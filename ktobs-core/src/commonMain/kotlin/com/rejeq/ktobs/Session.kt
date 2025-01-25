package com.rejeq.ktobs

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Represents an authenticated session with the OBS WebSocket server.
 * Handles message routing and UUID management for request/response pairs.
 *
 * @property ws The underlying WebSocket session
 * @property onEvent Optional callback for handling incoming events
 */
@OptIn(ExperimentalUuidApi::class)
class ObsSession internal constructor(
    val ws: WsSession,
    private val onEvent: (ObsSession.(event: EventOpCode) -> Unit)? = null,
) {
    private val activeUuids =
        mutableMapOf<Uuid, CompletableDeferred<RequestResponseOpCode>>()
    private val activeUuidLock = Mutex()

    private var identified: CompletableDeferred<Int>? = null

    /**
     * Acquires a unique UUID and executes the provided block with it.
     * Ensures proper cleanup of UUID after block completion.
     *
     * @param block The code block to execute with the acquired UUID and
     *        response handler
     * @return The result of the block execution
     * @throws UnableAcquireUuidException if unable to generate a unique UUID
     *         after multiple attempts
     */
    suspend fun <T> acquireUuid(
        block: suspend ObsSession.(
            uuid: Uuid,
            response: CompletableDeferred<RequestResponseOpCode>,
        ) -> T,
    ): T {
        val response = CompletableDeferred<RequestResponseOpCode>()
        lateinit var uniqueUuid: Uuid

        activeUuidLock.withLock {
            uniqueUuid =
                generateSequence { Uuid.random() }
                    .take(5)
                    .filter { !activeUuids.contains(it) }
                    .firstOrNull()
                    ?: throw UnableAcquireUuidException()

            activeUuids[uniqueUuid] = response
        }

        try {
            return block(uniqueUuid, response)
        } finally {
            activeUuids.remove(uniqueUuid)
        }
    }

    /**
     * Reidentifies the session with new event subscriptions.
     *
     * @param subs The new event subscriptions to request
     * @return The negotiated RPC version if reidentification was performed,
     *         null if already in progress
     * @throws Exception if exception is thrown by [WsSession] implementation
     */
    suspend fun reidentify(subs: ObsEventSubs): Int? {
        if (identified != null) {
            return null
        }

        identified = CompletableDeferred()
        ws.sendMessage(ReidentifyOpCode(subs.value))
        val negotiatedRpcVersion = identified?.await()
        identified = null

        return negotiatedRpcVersion
    }

    /**
     * Handles incoming messages from the OBS WebSocket server.
     *
     * Routes messages to appropriate handlers based on their type:
     * - IdentifiedOpCode: Completes the identification process
     * - RequestResponseOpCode: Routes to the corresponding request handler
     * - EventOpCode: Forwards to the event handler if configured
     *
     * @param msg The received OpCode message
     * @throws ReceivedUnexpectedOpCode if an unexpected message type is
     *         received
     */
    fun onReceiveMessage(msg: OpCode) {
        when (msg) {
            is IdentifiedOpCode -> {
                identified?.complete(msg.negotiatedRpcVersion)
            }
            is RequestResponseOpCode -> {
                val response = activeUuids[Uuid.parse(msg.requestId)]
                response?.complete(msg)
            }
            is EventOpCode -> {
                onEvent?.invoke(this, msg)
            }
            else -> {
                // Other OpCodes must be sent from client
                throw ReceivedUnexpectedOpCode(msg)
            }
        }
    }
}

/**
 * Exception thrown when unable to generate a unique UUID after multiple
 * attempts
 */
class UnableAcquireUuidException : Exception("Unable to generate unique uuid")

/**
 * Exception thrown when an unexpected OpCode is received from the server
 *
 * @property op The unexpected OpCode that was received
 */
class ReceivedUnexpectedOpCode(
    op: OpCode,
) : Exception("Received unexpected op code: $op")

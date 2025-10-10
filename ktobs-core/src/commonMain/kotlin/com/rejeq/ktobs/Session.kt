package com.rejeq.ktobs

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.json.decodeFromJsonElement
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
    private val onEvent: ObsEventHandler? = null,
) {
    private val activeUuids =
        mutableUuidStore<CompletableDeferred<RequestResponseOpCode>>()
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
     */
    suspend fun <T> acquireUuid(
        block: suspend ObsSession.(
            uuid: Uuid,
            response: Deferred<RequestResponseOpCode>,
        ) -> T,
    ): T {
        val response = CompletableDeferred<RequestResponseOpCode>()

        val uniqueUuid =
            activeUuidLock.withLock {
                activeUuids.register(response)
            }

        try {
            return block(uniqueUuid, response)
        } finally {
            activeUuidLock.withLock {
                activeUuids.remove(uniqueUuid)
            }
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
     * Cancels all pending request operations by cancelling their associated
     * deferred responses. This is typically called when the session is being
     * terminated or when a critical error occurs.
     *
     * @param e The [CancellationException] that triggered the cancellation.
     *        This exception will be propagated to all pending operations.
     * @throws Nothing This method does not throw exceptions directly, though
     *         the cancellation may trigger exception handlers in coroutines
     *         waiting for responses.
     */
    suspend fun cancelAllUuids(e: CancellationException) =
        activeUuidLock.withLock {
            activeUuids.forEach { it.value.cancel(e) }
        }

    /**
     * Deserializes the [EventOpCode.eventData] into an instance of the
     * specified type [Data].
     *
     * @return The deserialized [Data] object of the expected type.
     * @throws IllegalStateException if [EventOpCode.eventData] is `null`.
     *
     * @see kotlinx.serialization.json.Json.decodeFromJsonElement
     */
    inline fun <reified Data : Any> EventOpCode.get(): Data {
        // TODO: Use context parameters in EventOpCode when it become stable
        checkNotNull(eventData) { "eventData should not be null" }

        return ws.json.decodeFromJsonElement(eventData)
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
     * @throws ReceivedUnexpectedUuid if an incorrect uuid that cannot be parsed
     *         is received
     */
    suspend fun onReceiveMessage(msg: OpCode) {
        when (msg) {
            is IdentifiedOpCode -> {
                identified?.complete(msg.negotiatedRpcVersion)
            }
            is RequestResponseOpCode -> {
                val uuid = tryParseUuid(msg.requestId)
                val response = activeUuids[uuid]
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

@OptIn(ExperimentalUuidApi::class)
private fun tryParseUuid(uuidString: String): Uuid =
    try {
        Uuid.parse(uuidString)
    } catch (e: IllegalArgumentException) {
        throw ReceivedUnexpectedUuid(e)
    }

/**
 * Exception thrown when an unexpected OpCode is received from the server
 *
 * @property op The unexpected OpCode that was received
 */
class ReceivedUnexpectedOpCode(
    op: OpCode,
) : IllegalArgumentException("Received unexpected op code: $op")

/**
 * Exception thrown when an unexpected OpCode is received from the server
 *
 * @property cause The cause of this exception
 */
class ReceivedUnexpectedUuid(
    cause: Exception,
) : IllegalArgumentException(cause)

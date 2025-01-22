package com.rejeq.ktobs.ktor

import com.rejeq.ktobs.*
import io.ktor.client.plugins.websocket.*
import kotlinx.coroutines.channels.ClosedReceiveChannelException

/**
 * Ktor-based implementation of [WsSession] interface.
 * Wraps a Ktor WebSocket session to provide OBS WebSocket protocol support.
 *
 * @property ws The underlying Ktor WebSocket session
 */
class KtorWsSession(
    val ws: DefaultClientWebSocketSession,
) : WsSession {
    override suspend fun receiveMessage(): ObsMessage =
        ws.receiveDeserialized<ObsMessage>()

    override suspend fun sendMessage(msg: ObsMessage) = ws.sendSerialized(msg)

    override suspend fun getCloseReason(): ObsCloseReason? =
        ws.closeReason.await()?.let { reason ->
            val code =
                ObsCloseCode.entries.find { it.value == reason.code }
                    ?: ObsCloseCode.UnknownReason

            ObsCloseReason(code, reason.message)
        }

    /** Closes the underlying Ktor HTTP client */
    fun close() = ws.call.client.close()
}

/**
 * Runs a message receiver loop for an OBS session.
 * Continuously receives messages and routes them through the session's message
 * handler until the connection is closed.
 */
suspend fun ObsSession.runReceiver() =
    try {
        while (true) {
            val msg = ws.receiveMessage<OpCode>()
            onReceiveMessage(msg)
        }
    } catch (e: ClosedReceiveChannelException) {
    }

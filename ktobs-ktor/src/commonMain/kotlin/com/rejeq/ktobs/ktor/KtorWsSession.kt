package com.rejeq.ktobs.ktor

import com.rejeq.ktobs.*
import io.ktor.client.plugins.websocket.*
import io.ktor.websocket.CloseReason
import io.ktor.websocket.close
import kotlinx.coroutines.CancellationException

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
            ObsCloseReason(reason.code, reason.message)
        }

    /** Closes OBS session */
    suspend fun close(
        reason: CloseReason = CloseReason(CloseReason.Codes.NORMAL, ""),
    ) {
        ws.close(reason)
        ws.incoming.cancel()
    }
}

/**
 * Runs a message receiver loop for an OBS session.
 * Continuously receives messages and routes them through the session's message
 * handler until the connection is closed. When connection is closed it cancel
 * any pending request
 */
suspend fun ObsSession.runReceiver() =
    try {
        while (true) {
            val msg = ws.receiveOpCode<OpCode>()
            onReceiveMessage(msg)
        }
    } finally {
        cancelAllUuids(CancellationException("Session receiver was closed"))
    }

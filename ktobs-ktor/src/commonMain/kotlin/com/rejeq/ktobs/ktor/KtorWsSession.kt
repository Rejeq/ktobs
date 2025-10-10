package com.rejeq.ktobs.ktor

import com.rejeq.ktobs.ObsCloseReason
import com.rejeq.ktobs.ObsMessage
import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.OpCode
import com.rejeq.ktobs.ReceivedUnexpectedOpCode
import com.rejeq.ktobs.ReceivedUnexpectedUuid
import com.rejeq.ktobs.WsSession
import com.rejeq.ktobs.receiveOpCode
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.receiveDeserialized
import io.ktor.client.plugins.websocket.sendSerialized
import io.ktor.serialization.WebsocketDeserializeException
import io.ktor.websocket.CloseReason
import io.ktor.websocket.close
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

/**
 * Ktor-based implementation of [WsSession] interface.
 * Wraps a Ktor WebSocket session to provide OBS WebSocket protocol support.
 *
 * @property ws The underlying Ktor WebSocket session
 */
class KtorWsSession(
    val ws: DefaultClientWebSocketSession,
    override val json: Json,
) : WsSession {
    override suspend fun receiveMessage(): ObsMessage =
        try {
            ws.receiveDeserialized<ObsMessage>()
        } catch (e: WebsocketDeserializeException) {
            throw SerializationException(e)
        }

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
 *
 * This implementation will silently ignore some non-critical errors,
 */
suspend fun ObsSession.runReceiver() =
    try {
        while (true) {
            val msg =
                try {
                    session.ws.receiveOpCode<OpCode>()
                } catch (_: SerializationException) {
                    continue
                }

            try {
                session.onReceiveMessage(msg)
            } catch (_: ReceivedUnexpectedOpCode) {
            } catch (_: ReceivedUnexpectedUuid) {
            }
        }
    } finally {
        session.cancelAllUuids(
            CancellationException("Session receiver was closed"),
        )
    }

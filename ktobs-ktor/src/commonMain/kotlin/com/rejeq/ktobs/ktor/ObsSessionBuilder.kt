package com.rejeq.ktobs.ktor

import com.rejeq.ktobs.EventOpCode
import com.rejeq.ktobs.ObsAuthException
import com.rejeq.ktobs.ObsEventSub
import com.rejeq.ktobs.ObsEventSubs
import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.authSession
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.*
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Builder class for creating and configuring OBS WebSocket sessions using Ktor.
 *
 * @property client The Ktor HTTP client used for WebSocket connections
 */
class ObsSessionBuilder(
    val client: HttpClient,
) {
    /** The hostname of the server */
    var host: String? = null

    /** The port number of server */
    var port: Int? = null

    /** The password for authentication, if required */
    var password: String? = null

    /** Event subscriptions to request during session initialization */
    var eventSubs: ObsEventSubs = ObsEventSub.None()

    /** Callback for handling received events */
    var onEvent: (ObsSession.(event: EventOpCode) -> Unit)? = null

    /**
     * Creates and initializes a new session.
     *
     * @param ctx The coroutine context for running the session
     * @return An authenticated [ObsSession]
     * @throws ObsAuthException if authentication fails
     * @throws Exception if connection fails
     */
    suspend fun build(
        ctx: CoroutineContext = EmptyCoroutineContext,
    ): ObsSession {
        val wsRaw =
            client.webSocketSession(
                method = HttpMethod.Get,
                host = host,
                port = port,
            ) {
                header(
                    HttpHeaders.SecWebSocketProtocol,
                    "obswebsocket.json",
                )
            }

        val ws = KtorWsSession(wsRaw)
        val session = ws.authSession(password, eventSubs)

        CoroutineScope(ctx).launch {
            session.runReceiver()
        }

        return session
    }

    /**
     * Creates a session and executes the provided block within its scope.
     * The session is automatically closed when the block completes.
     *
     * @param block The code block to execute within the session
     * @throws ObsAuthException if authentication fails
     * @throws Exception if connection fails
     */
    suspend fun connect(block: suspend ObsSession.() -> Unit) {
        client.webSocket(
            method = HttpMethod.Get,
            host = host,
            port = port,
            request = {
                header(
                    HttpHeaders.SecWebSocketProtocol,
                    "obswebsocket.json",
                )
            },
        ) {
            val ws = KtorWsSession(this)
            val session = ws.authSession(password, eventSubs, onEvent)

            launch {
                session.runReceiver()
            }

            session.block()
        }
    }
}

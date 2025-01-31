package com.rejeq.ktobs

import com.rejeq.ktobs.ktor.ObsSessionBuilder
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.*
import kotlinx.serialization.json.Json
import kotlin.test.assertEquals

fun runObsTest(
    setup: suspend (ObsSession).() -> Unit = {},
    cleanup: suspend (ObsSession).() -> Unit = {},
    test: suspend (ObsSession).() -> Unit,
) {
    val client =
        httpClient {
            install(WebSockets) {
                pingIntervalMillis = 20_000
                contentConverter =
                    KotlinxWebsocketSerializationConverter(Json)
            }
        }

    runBlocking {
        val session =
            ObsSessionBuilder(client).apply {
                host = "127.0.0.1"
                port = 4455
                password = "12345678"
                eventSubs = ObsEventSubs.None
            }

        session.connect {
            try {
                setup()
                test()
            } finally {
                cleanup()
            }
        }
    }
}

suspend fun tryObsRequest(block: suspend () -> Unit) =
    try {
        block()
    } catch (e: ObsRequestException) {
        println("Ignoring: $e")
    }

inline fun assertRequestFailsWith(
    code: RequestCode,
    block: () -> Unit,
) {
    try {
        block()
    } catch (e: ObsRequestException) {
        assertEquals(
            e.code,
            code,
            "Request must fail with '$code' code, but got: $e",
        )
    }
}

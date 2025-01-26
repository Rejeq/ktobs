package com.rejeq.ktobs

import com.rejeq.ktobs.ktor.KtorWsSession
import com.rejeq.ktobs.ktor.ObsSessionBuilder
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.*
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

abstract class ObsTest {
    protected lateinit var session: ObsSession

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

    @BeforeTest
    fun setUp() {
        val client =
            httpClient {
                install(WebSockets) {
                    pingIntervalMillis = 20_000
                    contentConverter =
                        KotlinxWebsocketSerializationConverter(Json)
                }
            }

        runBlocking {
            session =
                ObsSessionBuilder(client).let {
                    it.host = "127.0.0.1"
                    it.port = 4455
                    it.password = "12345678"
                    it.eventSubs = ObsEventSub.None

                    it.build(Dispatchers.Default)
                }
        }
    }

    @AfterTest
    fun tearDown() {
        if (::session.isInitialized) {
            runBlocking {
                val ws = session.ws
                if (ws is KtorWsSession) {
                    ws.close()
                }
            }
        }
    }
}

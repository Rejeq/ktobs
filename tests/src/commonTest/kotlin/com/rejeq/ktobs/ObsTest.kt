package com.rejeq.ktobs

import com.rejeq.ktobs.ktor.ObsSessionBuilder
import com.rejeq.ktobs.request.general.getVersion
import io.ktor.client.plugins.websocket.*
import io.ktor.serialization.kotlinx.*
import kotlin.test.assertEquals
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json

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

    runTest {
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
                println("Clean up started")
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

inline fun requestCanFailWith(
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

inline fun waitUntil(block: () -> Boolean) {
    while (true) {
        if (block()) {
            break
        }
    }
}

suspend fun ObsSession.isVersionSupported(version: String): Boolean {
    val wsVersion = getObsWsVersion()
    val supported = isVersionGreater(wsVersion, version)
    println("isVersionSupported($version) -> $supported")

    return supported
}

private fun isVersionGreater(version1: String, version2: String): Boolean {
    val v1Parts = version1.split(".").map { it.toIntOrNull() ?: 0 }
    val v2Parts = version2.split(".").map { it.toIntOrNull() ?: 0 }

    for (i in 0 until maxOf(v1Parts.size, v2Parts.size)) {
        val part1 = v1Parts.getOrElse(i) { 0 }
        val part2 = v2Parts.getOrElse(i) { 0 }

        if (part1 > part2) return true
        if (part1 < part2) return false
    }

    return false
}

private suspend fun ObsSession.getObsWsVersion(): String {
    if (CACHED_WEBSOCKET_VERSION == null) {
        WEBSCOKET_VERSION_LOCK.withLock {
            if (CACHED_WEBSOCKET_VERSION == null) {
                CACHED_WEBSOCKET_VERSION = getVersion().obsWebSocketVersion
            }
        }
    }

    return CACHED_WEBSOCKET_VERSION!!
}

private val WEBSCOKET_VERSION_LOCK = Mutex()
private var CACHED_WEBSOCKET_VERSION: String? = null

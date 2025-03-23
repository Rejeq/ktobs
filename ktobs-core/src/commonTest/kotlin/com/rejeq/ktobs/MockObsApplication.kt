package com.rejeq.ktobs

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

data class MockSession(
    val serverToClient: Channel<ObsMessage> = Channel(Channel.UNLIMITED),
    val clientToServer: Channel<ObsMessage> = Channel(Channel.UNLIMITED),
)

fun mockObsApplication(
    serverBehavior: suspend MockServerWsSession.() -> Unit,
    clientBehavior: suspend WsSession.() -> Unit,
) = runTest {
    val session = MockSession()

    val serverJob =
        launch {
            val server = MockServerWsSession(session)
            serverBehavior(server)
        }

    val client = MockClientWsSession(session)
    clientBehavior(client)

    // Cancel server session if client returned early (e.g. exception threw)
    serverJob.cancel()
}

class MockServerWsSession(
    val session: MockSession,
) {
    @OptIn(InternalSerializationApi::class)
    suspend inline fun <reified T : OpCode> sendMessage(data: T) {
        val msg =
            ObsMessage(
                getOpCode(data),
                Json.encodeToJsonElement(T::class.serializer(), data),
            )

        session.serverToClient.send(msg)
    }

    suspend inline fun <reified T : OpCode> receiveOpCode(): T {
        val msg = session.clientToServer.receive()
        val serializer = getSerializer(msg.op)

        return Json.decodeFromJsonElement(serializer, msg.d) as T
    }
}

class MockClientWsSession(
    private val session: MockSession,
) : WsSession {
    override suspend fun receiveMessage(): ObsMessage {
        val msg = session.serverToClient.receive()

        return msg
    }

    override suspend fun sendMessage(msg: ObsMessage) {
        session.clientToServer.send(msg)
    }

    override suspend fun getCloseReason(): ObsCloseReason? = null
}

package com.rejeq.ktobs

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class AuthMockSessionTest {
    @Test
    fun testServerWithAuth() =
        mockAuth(
            authentication = Authentication("challenge", "salt"),
            encodedPassword = "zTM5ki6L2vVvBQiTG9ckH1Lh64AbnCf6XZ226UmnkIA=",
        ) {
            authSession("password")
        }

    @Test
    fun testClientWithoutPassword() =
        mockAuth(
            authentication = Authentication("challenge", "salt"),
            encodedPassword = "zTM5ki6L2vVvBQiTG9ckH1Lh64AbnCf6XZ226UmnkIA=",
        ) {
            val e =
                assertFailsWith<ObsAuthException> {
                    authSession(password = null)
                }

            assertEquals(e.kind, AuthError.PasswordRequired)
        }

    @Test
    fun testClientWithPasswordAndServerWithoutPassword() =
        mockAuth(
            authentication = null,
        ) {
            // Password must not be sent to the server
            authSession(password = "secret")
        }

    @Test
    fun testServerWithoutAuth() =
        mockAuth(authentication = null) {
            authSession(password = null)
        }

    @Test
    fun testServerWithBigRpc() =
        mockAuth(
            rpcVersion = 6,
            negotiatedRpcVersion = 1,
        ) {
            authSession(password = null)
        }

    @Test
    fun testServerWithInvalidNegotiatedRpc() =
        mockAuth(
            rpcVersion = 1,
            negotiatedRpcVersion = -4,
        ) {
            val e =
                assertFailsWith<ObsAuthException> {
                    authSession(password = null)
                }

            assertEquals(e.kind, AuthError.InvalidRpc(-4))
        }

    @Test
    fun testServerWithBigNegotiatedRpc() =
        mockAuth(
            rpcVersion = 1,
            negotiatedRpcVersion = 7,
        ) {
            val e =
                assertFailsWith<ObsAuthException> {
                    authSession(password = null)
                }

            assertEquals(e.kind, AuthError.InvalidRpc(7))
        }
}

private fun mockAuth(
    rpcVersion: Int = 1,
    negotiatedRpcVersion: Int = 1,
    obsWebSocketVersion: String = "5.5.4",
    authentication: Authentication? = null,
    encodedPassword: String? = null,
    clientBehavior: suspend WsSession.() -> Unit,
) {
    mockObsApplication(
        serverBehavior = {
            authSessionBehavior(
                rpcVersion,
                negotiatedRpcVersion,
                obsWebSocketVersion,
                authentication,
                encodedPassword,
            )
        },
        clientBehavior = clientBehavior,
    )
}

private suspend fun MockServerWsSession.authSessionBehavior(
    rpcVersion: Int,
    negotiatedRpcVersion: Int,
    obsWebSocketVersion: String,
    authentication: Authentication?,
    encodedPassword: String?,
) {
    sendMessage(HelloOpCode(obsWebSocketVersion, rpcVersion, authentication))

    val identify = receiveOpCode<IdentifyOpCode>()
    val auth = authentication?.let { encodedPassword }
    assertEquals(
        identify.authentication,
        auth,
        "password must be equal to encoded password",
    )

    sendMessage(IdentifiedOpCode(negotiatedRpcVersion))
}

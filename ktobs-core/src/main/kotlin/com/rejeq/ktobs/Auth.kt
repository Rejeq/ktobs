package com.rejeq.ktobs

import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.serialization.Serializable
import java.security.MessageDigest
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

const val RPC_VERSION: Int = 1

sealed class AuthError(
    val msg: String,
) {
    /** Indicates that a password was required but not provided */
    data object PasswordRequired : AuthError("Password not provided")

    /** Indicates that the provided password was incorrect */
    data object InvalidPassword : AuthError("Invalid password")

    /**
     * Indicates that the RPC version is not compatible
     *
     * @property endpointVersion The incompatible RPC version received from the
     *           server
     */
    data class InvalidRpc(
        val endpointVersion: Int,
    ) : AuthError("Not supported OBS RPC version '$endpointVersion'")

    /**
     * Represents an unexpected authentication error
     *
     * @property reason The reason for connection closure
     * @property e The exception that caused the authentication failure
     */
    data class Unexpected(
        val reason: ObsCloseReason?,
        val e: ClosedReceiveChannelException,
    ) : AuthError("Unexpected auth error: $reason ($e)")
}

class AuthException(
    val kind: AuthError,
) : Exception(kind.msg)

@Serializable
class HelloOpCode(
    val obsWebSocketVersion: String,
    val rpcVersion: Int,
    val authentication: Authentication? = null,
) : OpCode

@Serializable
class IdentifyOpCode(
    val rpcVersion: Int,
    val authentication: String? = null,
    val eventSubscriptions: Int? = null,
) : OpCode

@Serializable
class IdentifiedOpCode(
    val negotiatedRpcVersion: Int,
) : OpCode

@Serializable
class ReidentifyOpCode(
    val eventSubscriptions: Int? = null,
) : OpCode

@Serializable
class Authentication(
    val challenge: String,
    val salt: String,
)

/**
 * Authenticates a WebSocket session with the OBS server
 *
 * @param password The password for authentication, can be null if server
 *        doesn't require auth
 * @param eventSubs The event subscriptions to request, defaults to None
 * @return An authenticated [ObsSession]
 * @throws AuthException if authentication fails for any reason
 * @throws Exception if exception is thrown by [WsSession] implementation
 */
suspend fun WsSession.authSession(
    password: String?,
    eventSubs: ObsEventSubs = ObsEventSub.None(),
    onEvent: (ObsSession.(event: EventOpCode) -> Unit)? = null,
): ObsSession {
    try {
        val hello = receiveMessage<HelloOpCode>()

        val auth =
            hello.authentication?.let { auth ->
                if (password == null) {
                    throw AuthException(AuthError.PasswordRequired)
                }

                encodeAuthPassword(auth, password)
            }

        identifySession(auth, eventSubs)
        return ObsSession(this, onEvent)
    } catch (e: ClosedReceiveChannelException) {
        val reason = getCloseReason()
        throw AuthException(AuthError.Unexpected(reason, e))
    }
}

private suspend fun WsSession.identifySession(
    auth: String?,
    eventSubs: ObsEventSubs,
) {
    try {
        sendMessage(IdentifyOpCode(RPC_VERSION, auth, eventSubs.value))

        val identified = receiveMessage<IdentifiedOpCode>()
        val selectedRpc = identified.negotiatedRpcVersion

        if (selectedRpc <= 0 || selectedRpc > RPC_VERSION) {
            throw AuthException(AuthError.InvalidRpc(selectedRpc))
        }
    } catch (e: ClosedReceiveChannelException) {
        when (getCloseReason()?.code) {
            // AuthenticationFailed can also be thrown if we do not provide
            // password when it required or when password field is not a
            // string. We handle both cases, so we can be sure that the password
            // is incorrect
            ObsCloseCode.AuthenticationFailed ->
                throw AuthException(AuthError.InvalidPassword)

            ObsCloseCode.UnsupportedRpcVersion ->
                throw AuthException(AuthError.InvalidRpc(RPC_VERSION))

            else -> throw e
        }
    }
}

internal fun encodeAuthPassword(
    auth: Authentication,
    password: String,
): String {
    val secret = base64(sha256(password + auth.salt))
    return base64(sha256(secret + auth.challenge))
}

@OptIn(ExperimentalEncodingApi::class)
private fun base64(str: ByteArray): String = Base64.Default.encode(str)

private fun sha256(str: String): ByteArray =
    MessageDigest.getInstance("SHA-256").digest(str.toByteArray())

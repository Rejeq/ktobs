package com.rejeq.ktobs

/**
 * Represents the reason for a WebSocket connection closure.
 *
 * @property code The specific close code indicating why the connection was
 *           terminated.
 *           Can be either standard WebSocket RPC close codes (1000-2999) or
 *           OBS-specific codes defined in [ObsCloseCode] (4000+).
 * @property message Additional details about the closure
 */
data class ObsCloseReason(
    val code: Short,
    val message: String,
)

/**
 * Enumeration of possible WebSocket closure codes from the OBS WebSocket
 * server.
 *
 * @property value The numeric value of the close code
 */
enum class ObsCloseCode(
    val value: Short,
) {
    /**
     * For internal use only to tell the request handler not to perform any
     * close action.
     */
    DontClose(0),

    /** Unknown reason, should never be used. */
    UnknownReason(4000),

    /** The server was unable to decode the incoming websocket message. */
    MessageDecodeError(4002),

    /** A data field is required but missing from the payload. */
    MissingDataField(4003),

    /** A data field's value type is invalid. */
    InvalidDataFieldType(4004),

    /** A data field's value is invalid. */
    InvalidDataFieldValue(4005),

    /** The specified `op` was invalid or missing. */
    UnknownOpCode(4006),

    /**
     * The client sent a websocket message without first sending `Identify`
     * message.
     */
    NotIdentified(4007),

    /**
     * The client sent an `Identify` message while already identified.
     *
     * Note: Once a client has identified, only `Reidentify` may be used to
     * change session parameters.
     */
    AlreadyIdentified(4008),

    /** The authentication attempt (via `Identify`) failed. */
    AuthenticationFailed(4009),

    /**
     * The server detected the usage of an old version of the obs-websocket RPC
     * protocol.
     */
    UnsupportedRpcVersion(4010),

    /**
     * The websocket session has been invalidated by the obs-websocket server.
     *
     * Note: This is the code used by the `Kick` button in the UI Session List.
     * If you receive this code, you must not automatically reconnect.
     */
    SessionInvalidated(4011),

    /**
     * A requested feature is not supported due to hardware/software
     * limitations.
     */
    UnsupportedFeature(4012),
    ;

    companion object {
        fun find(code: Short?): ObsCloseCode? =
            code?.let {
                ObsCloseCode.entries.find { it.value == code }
            }
    }
}

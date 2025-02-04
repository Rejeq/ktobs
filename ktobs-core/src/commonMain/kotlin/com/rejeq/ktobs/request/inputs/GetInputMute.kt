package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
class GetInputMuteRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
)

@Serializable
class GetInputMuteResponse(
    val inputMuted: Boolean,
)

/**
 * Gets the audio mute state of an input.
 *
 * @param inputName Name of input to get the mute state of
 * @param inputUuid UUID of input to get the mute state of
 * @return True == Input is muted, False == Input is unmuted
 */
suspend fun ObsSession.getInputMute(
    inputName: String? = null,
    inputUuid: String? = null,
): Boolean =
    callMethod<GetInputMuteResponse, GetInputMuteRequest>(
        "GetInputMute",
        GetInputMuteRequest(inputName, inputUuid),
    ).inputMuted

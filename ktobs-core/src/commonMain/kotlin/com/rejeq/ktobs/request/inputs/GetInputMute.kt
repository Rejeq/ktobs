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

// Gets the audio mute state of an input
suspend fun ObsSession.getInputMute(
    inputName: String? = null,
    inputUuid: String? = null,
): Boolean =
    callMethod<GetInputMuteResponse, GetInputMuteRequest>(
        "GetInputMute",
        GetInputMuteRequest(inputName, inputUuid),
    ).inputMuted

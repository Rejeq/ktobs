package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
data class ToggleInputMuteRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
)

@Serializable
data class ToggleInputMuteResponse(
    val inputMuted: Boolean,
)

// Toggles the audio mute state of an input
suspend fun ObsSession.toggleInputMute(
    inputName: String? = null,
    inputUuid: String? = null,
): Boolean =
    callMethod<ToggleInputMuteResponse, ToggleInputMuteRequest>(
        "ToggleInputMute",
        ToggleInputMuteRequest(inputName, inputUuid),
    ).inputMuted

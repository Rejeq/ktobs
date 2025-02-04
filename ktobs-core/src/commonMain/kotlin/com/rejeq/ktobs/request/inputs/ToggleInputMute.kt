package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
class ToggleInputMuteRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
)

@Serializable
class ToggleInputMuteResponse(
    val inputMuted: Boolean,
)

/**
 * Toggles the audio mute state of an input.
 *
 * @param inputName Name of the input to toggle the mute state of
 * @param inputUuid UUID of the input to toggle the mute state of
 * @return Whether the input has been muted or unmuted
 */
suspend fun ObsSession.toggleInputMute(
    inputName: String? = null,
    inputUuid: String? = null,
): Boolean =
    callMethod<ToggleInputMuteResponse, ToggleInputMuteRequest>(
        "ToggleInputMute",
        ToggleInputMuteRequest(inputName, inputUuid),
    ).inputMuted

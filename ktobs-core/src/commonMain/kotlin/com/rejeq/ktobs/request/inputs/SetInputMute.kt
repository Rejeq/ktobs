package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetInputMuteRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
    val inputMuted: Boolean,
)

/**
 * Sets the audio mute state of an input.
 *
 * @param name Name of the input to set the mute state of
 * @param uuid UUID of the input to set the mute state of
 * @param muted True == Mute input, False == Unmute input
 */
suspend fun ObsSession.setInputMute(
    name: String? = null,
    uuid: String? = null,
    muted: Boolean,
) = callUnitMethod(
    "SetInputMute",
    SetInputMuteRequest(name, uuid, muted),
)

package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
class SetInputAudioTracksRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
    val inputAudioTracks: JsonElement,
)

/**
 * Gets the items of a list property from an input's properties.
 *
 * Note: Use this in cases where an input provides a dynamic, selectable list of
 * items. For example, display capture, where it provides a list of available
 * displays.
 *
 * @param name Name of the input
 * @param uuid UUID of the input
 * @param audioTracks Track settings to apply
 */
suspend fun ObsSession.setInputAudioTracks(
    name: String? = null,
    uuid: String? = null,
    audioTracks: JsonElement,
) = callUnitMethod(
    "SetInputAudioTracks",
    SetInputAudioTracksRequest(name, uuid, audioTracks),
)

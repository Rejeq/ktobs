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

// Sets the enable state of audio tracks of an input
suspend fun ObsSession.setInputAudioTracks(
    name: String? = null,
    uuid: String? = null,
    audioTracks: JsonElement,
) = callUnitMethod(
    "SetInputAudioTracks",
    SetInputAudioTracksRequest(name, uuid, audioTracks),
)

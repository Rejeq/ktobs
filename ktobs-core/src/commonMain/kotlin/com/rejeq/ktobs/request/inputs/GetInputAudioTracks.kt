package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class GetInputAudioTracksRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
)

@Serializable
data class GetInputAudioTracksResponse(
    val inputAudioTracks: JsonElement,
)

// Gets the enable state of all audio tracks of an input
suspend fun ObsSession.getInputAudioTracks(
    inputName: String? = null,
    inputUuid: String? = null,
): JsonElement =
    callMethod<GetInputAudioTracksResponse, GetInputAudioTracksRequest>(
        "GetInputAudioTracks",
        GetInputAudioTracksRequest(inputName, inputUuid),
    ).inputAudioTracks

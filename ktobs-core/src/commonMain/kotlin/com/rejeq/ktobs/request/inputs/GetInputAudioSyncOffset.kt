package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
class GetInputAudioSyncOffsetRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
)

@Serializable
class GetInputAudioSyncOffsetResponse(
    val inputAudioSyncOffset: Int,
)

// Gets the audio sync offset of an input
suspend fun ObsSession.getInputAudioSyncOffset(
    inputName: String? = null,
    inputUuid: String? = null,
): Int =
    callMethod<GetInputAudioSyncOffsetResponse, GetInputAudioSyncOffsetRequest>(
        "GetInputAudioSyncOffset",
        GetInputAudioSyncOffsetRequest(inputName, inputUuid),
    ).inputAudioSyncOffset

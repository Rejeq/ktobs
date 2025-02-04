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

/**
 * Gets the audio sync offset of an input.
 *
 * Note: The audio sync offset can be negative too!
 *
 * @param inputName Name of the input to get the audio sync offset of
 * @param inputUuid UUID of the input to get the audio sync offset of
 * @return Audio sync offset in milliseconds
 */
suspend fun ObsSession.getInputAudioSyncOffset(
    inputName: String? = null,
    inputUuid: String? = null,
): Int =
    callMethod<GetInputAudioSyncOffsetResponse, GetInputAudioSyncOffsetRequest>(
        "GetInputAudioSyncOffset",
        GetInputAudioSyncOffsetRequest(inputName, inputUuid),
    ).inputAudioSyncOffset

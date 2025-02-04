package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
class GetInputAudioBalanceRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
)

@Serializable
class GetInputAudioBalanceResponse(
    val inputAudioBalance: Double,
)

/**
 * Gets the audio balance of an input.
 *
 * @param inputName Name of the input to get the audio balance of
 * @param inputUuid UUID of the input to get the audio balance of
 * @return Audio balance value from 0.0 to 1.0
 */
suspend fun ObsSession.getInputAudioBalance(
    inputName: String? = null,
    inputUuid: String? = null,
): Double =
    callMethod<GetInputAudioBalanceResponse, GetInputAudioBalanceRequest>(
        "GetInputAudioBalance",
        GetInputAudioBalanceRequest(inputName, inputUuid),
    ).inputAudioBalance

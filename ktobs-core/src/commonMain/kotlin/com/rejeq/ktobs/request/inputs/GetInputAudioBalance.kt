package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
data class GetInputAudioBalanceRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
)

@Serializable
data class GetInputAudioBalanceResponse(
    val inputAudioBalance: Double,
)

// Gets the audio balance of an input
suspend fun ObsSession.getInputAudioBalance(
    inputName: String? = null,
    inputUuid: String? = null,
): Double =
    callMethod<GetInputAudioBalanceResponse, GetInputAudioBalanceRequest>(
        "GetInputAudioBalance",
        GetInputAudioBalanceRequest(inputName, inputUuid),
    ).inputAudioBalance

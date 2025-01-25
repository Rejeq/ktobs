package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
data class GetSpecialInputsResponse(
    val desktop1: String?,
    val desktop2: String?,
    val mic1: String?,
    val mic2: String?,
    val mic3: String?,
    val mic4: String?,
)

// Gets the names of all special inputs
suspend fun ObsSession.getSpecialInputs(): GetSpecialInputsResponse =
    callMethod("GetSpecialInputs")

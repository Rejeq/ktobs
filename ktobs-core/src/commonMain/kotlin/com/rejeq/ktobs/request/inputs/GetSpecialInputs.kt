package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

/**
 * @property desktop1 Name of the Desktop Audio input
 * @property desktop2 Name of the Desktop Audio 2 input
 * @property mic1 Name of the Mic/Auxiliary Audio input
 * @property mic2 Name of the Mic/Auxiliary Audio 2 input
 * @property mic3 Name of the Mic/Auxiliary Audio 3 input
 * @property mic4 Name of the Mic/Auxiliary Audio 4 input
 */
@Serializable
data class GetSpecialInputsResponse(
    val desktop1: String?,
    val desktop2: String?,
    val mic1: String?,
    val mic2: String?,
    val mic3: String?,
    val mic4: String?,
)

/**
 * Gets the names of all special inputs.
 */
suspend fun ObsSession.getSpecialInputs(): GetSpecialInputsResponse =
    callMethod("GetSpecialInputs")

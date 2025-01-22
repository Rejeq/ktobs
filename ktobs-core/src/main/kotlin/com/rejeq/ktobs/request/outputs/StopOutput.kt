package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class StopOutputRequest(
    val outputName: String,
)

// Stops an output
suspend fun ObsSession.stopOutput(outputName: String) =
    callUnitMethod("StopOutput", StopOutputRequest(outputName)) 

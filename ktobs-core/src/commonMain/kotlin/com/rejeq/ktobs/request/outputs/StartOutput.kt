package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class StartOutputRequest(
    val outputName: String,
)

// Starts an output
suspend fun ObsSession.startOutput(outputName: String) =
    callUnitMethod("StartOutput", StartOutputRequest(outputName))

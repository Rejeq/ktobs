package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class StartOutputRequest(
    val outputName: String,
)

/**
 * Starts an output.
 *
 * @param name Name of the output to start
 */
suspend fun ObsSession.startOutput(name: String) =
    callUnitMethod("StartOutput", StartOutputRequest(name))

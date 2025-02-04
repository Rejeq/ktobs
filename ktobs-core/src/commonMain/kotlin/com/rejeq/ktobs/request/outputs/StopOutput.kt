package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class StopOutputRequest(
    val outputName: String,
)

/**
 * Stops an output.
 *
 * @param name Name of the output to stop
 */
suspend fun ObsSession.stopOutput(name: String) =
    callUnitMethod("StopOutput", StopOutputRequest(name))

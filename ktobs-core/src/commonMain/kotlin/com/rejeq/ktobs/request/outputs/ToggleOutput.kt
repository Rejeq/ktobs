package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
class ToggleOutputRequest(
    val outputName: String,
)

@Serializable
class ToggleOutputResponse(
    val outputActive: Boolean,
)

// Toggles the status of an output
suspend fun ObsSession.toggleOutput(outputName: String): Boolean =
    callMethod<ToggleOutputResponse, ToggleOutputRequest>(
        "ToggleOutput",
        ToggleOutputRequest(outputName),
    ).outputActive

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

/**
 * Toggles the status of an output.
 *
 * @param name Name of the output to toggle
 * @return Whether the output is active after toggling
 */
suspend fun ObsSession.toggleOutput(name: String): Boolean =
    callMethod<ToggleOutputResponse, ToggleOutputRequest>(
        "ToggleOutput",
        ToggleOutputRequest(name),
    ).outputActive

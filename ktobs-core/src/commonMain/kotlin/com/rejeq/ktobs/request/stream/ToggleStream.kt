package com.rejeq.ktobs.request.stream

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ToggleStreamResponse(
    @SerialName("outputActive") val active: Boolean,
)

/**
 * Toggles the status of the stream output.
 *
 * @return Whether the output is active after toggling
 */
suspend fun ObsSession.toggleStream(): Boolean =
    callMethod<ToggleStreamResponse>("ToggleStream").active

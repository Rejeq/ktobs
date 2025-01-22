package com.rejeq.ktobs.request.stream

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ToggleStreamResponse(
    @SerialName("outputActive") val active: Boolean,
)

// Toggles the status of the stream output
suspend fun ObsSession.toggleStream(): ToggleStreamResponse =
    callMethod("ToggleStream")

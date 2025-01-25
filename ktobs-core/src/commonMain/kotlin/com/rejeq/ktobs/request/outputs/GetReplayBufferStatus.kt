package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetReplayBufferStatusResponse(
    @SerialName("outputActive") val active: Boolean,
)

// Gets the status of the replay buffer output
suspend fun ObsSession.getReplayBufferStatus(): Boolean =
    callMethod<GetReplayBufferStatusResponse>("GetReplayBufferStatus").active 

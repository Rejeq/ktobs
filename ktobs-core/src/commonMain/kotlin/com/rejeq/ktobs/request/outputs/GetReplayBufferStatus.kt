package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GetReplayBufferStatusResponse(
    @SerialName("outputActive") val active: Boolean,
)

/**
 * Gets the status of the replay buffer output.
 *
 * @return Whether the output is active
 */
suspend fun ObsSession.getReplayBufferStatus(): Boolean =
    callMethod<GetReplayBufferStatusResponse>("GetReplayBufferStatus").active

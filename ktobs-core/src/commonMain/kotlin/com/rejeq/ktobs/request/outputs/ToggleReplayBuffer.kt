package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ToggleReplayBufferResponse(
    @SerialName("outputActive") val active: Boolean,
)

// Toggles the state of the replay buffer output
suspend fun ObsSession.toggleReplayBuffer(): Boolean =
    callMethod<ToggleReplayBufferResponse>("ToggleReplayBuffer").active

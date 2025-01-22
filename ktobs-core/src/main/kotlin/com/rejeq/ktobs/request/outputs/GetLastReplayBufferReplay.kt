package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
data class GetLastReplayBufferReplayResponse(
    val savedReplayPath: String,
)

// Gets the filename of the last replay buffer save file
suspend fun ObsSession.getLastReplayBufferReplay(): String =
    callMethod<GetLastReplayBufferReplayResponse>(
        "GetLastReplayBufferReplay",
    ).savedReplayPath 

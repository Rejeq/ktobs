package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod

// Starts the replay buffer output
suspend fun ObsSession.startReplayBuffer() =
    callUnitMethod(
        "StartReplayBuffer",
    ) 

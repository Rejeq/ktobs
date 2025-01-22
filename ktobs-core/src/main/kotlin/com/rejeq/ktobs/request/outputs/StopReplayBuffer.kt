package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod

// Stops the replay buffer output
suspend fun ObsSession.stopReplayBuffer() = callUnitMethod("StopReplayBuffer") 

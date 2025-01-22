package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod

// Saves the contents of the replay buffer output
suspend fun ObsSession.saveReplayBuffer() = callUnitMethod("SaveReplayBuffer") 

package com.rejeq.ktobs.request.record

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod

// Pauses the record output
suspend fun ObsSession.pauseRecord() = callUnitMethod("PauseRecord") 

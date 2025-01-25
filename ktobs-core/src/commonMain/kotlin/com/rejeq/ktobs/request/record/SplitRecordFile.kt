package com.rejeq.ktobs.request.record

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod

// Splits the current recording file
suspend fun ObsSession.splitRecordFile() = callUnitMethod("SplitRecordFile") 

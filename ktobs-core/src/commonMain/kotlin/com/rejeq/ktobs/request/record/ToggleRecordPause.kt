package com.rejeq.ktobs.request.record

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod

// Toggles pause on the record output
suspend fun ObsSession.toggleRecordPause() = callUnitMethod("ToggleRecordPause")

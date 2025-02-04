package com.rejeq.ktobs.request.record

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod

/**
 * Starts the record output.
 */
suspend fun ObsSession.startRecord() = callUnitMethod("StartRecord") 

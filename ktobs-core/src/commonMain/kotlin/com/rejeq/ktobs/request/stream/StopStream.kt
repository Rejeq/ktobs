package com.rejeq.ktobs.request.stream

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod

// Stops the stream output
suspend fun ObsSession.stopStream() {
    callUnitMethod("StopStream")
}

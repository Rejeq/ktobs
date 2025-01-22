package com.rejeq.ktobs.request.stream

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod

// Starts the stream output
suspend fun ObsSession.startStream() {
    callUnitMethod("StartStream")
}

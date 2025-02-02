package com.rejeq.ktobs.request.general

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SleepRequest(
    val sleepMillis: Long? = null,
    val sleepFrames: Int? = null,
)

// Sleeps for a time duration in milliseconds.
// Only available in request batches
suspend fun ObsSession.sleep(millis: Long) {
    callUnitMethod("Sleep", SleepRequest(millis, null))
}

// Sleeps for a time duration in number of frames.
// Only available in request batches
suspend fun ObsSession.sleepFrames(frames: Int) {
    callUnitMethod("Sleep", SleepRequest(null, frames))
}

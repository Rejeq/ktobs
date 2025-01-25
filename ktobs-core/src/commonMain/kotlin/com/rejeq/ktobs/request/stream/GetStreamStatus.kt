package com.rejeq.ktobs.request.stream

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetStreamStatusResponse(
    @SerialName("outputActive") val active: Boolean,
    @SerialName("outputReconnecting") val reconnecting: Boolean,
    @SerialName("outputTimecode") val timecode: String,
    @SerialName("outputDuration") val duration: Long,
    @SerialName("outputCongestion") val congestion: Double,
    @SerialName("outputBytes") val bytes: Long,
    @SerialName("outputSkippedFrames") val skippedFrames: Int,
    @SerialName("outputTotalFrames") val totalFrames: Int,
)

// Gets the status of the stream output
suspend fun ObsSession.getStreamStatus(): GetStreamStatusResponse =
    callMethod("GetStreamStatus")

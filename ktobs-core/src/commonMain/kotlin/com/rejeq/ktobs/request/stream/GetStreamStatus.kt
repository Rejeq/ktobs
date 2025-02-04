package com.rejeq.ktobs.request.stream

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property active Whether the output is active
 * @property reconnecting Whether the output is currently reconnecting
 * @property timecode Current formatted timecode string for the output
 * @property duration Current duration in milliseconds for the output
 * @property congestion Current congestion for the output
 * @property bytes Number of bytes sent by the output
 * @property skippedFrames Number of frames skipped by the output due to
 *           encoding lag
 * @property totalFrames Total number of frames delivered by the output
 */
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

/**
 * Gets the status of the stream output.
 */
suspend fun ObsSession.getStreamStatus(): GetStreamStatusResponse =
    callMethod("GetStreamStatus")

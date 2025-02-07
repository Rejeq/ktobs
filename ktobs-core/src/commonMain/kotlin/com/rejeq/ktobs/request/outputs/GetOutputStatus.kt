package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GetOutputStatusRequest(
    val outputName: String,
)

/**
 * @property active Whether the output is active
 * @property reconnecting Whether the output is reconnecting
 * @property timecode Current formatted timecode string for the output
 * @property duration Current duration in milliseconds for the output
 * @property congestion Congestion of the output
 * @property bytes Number of bytes sent by the output
 * @property skippedFrames Number of frames skipped by the output's process
 * @property totalFrames Total number of frames delivered by the output's process
 */
@Serializable
data class GetOutputStatusResponse(
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
 * Gets the status of an output.
 *
 * @param name Name of the output to get the status of
 */
suspend fun ObsSession.getOutputStatus(name: String): GetOutputStatusResponse =
    callMethod("GetOutputStatus", GetOutputStatusRequest(name))

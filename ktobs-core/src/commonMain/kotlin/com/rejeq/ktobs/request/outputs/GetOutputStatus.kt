package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GetOutputStatusRequest(
    val outputName: String,
)

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

// Gets the status of an output
suspend fun ObsSession.getOutputStatus(name: String): GetOutputStatusResponse =
    callMethod("GetOutputStatus", GetOutputStatusRequest(name))

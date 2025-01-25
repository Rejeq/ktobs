package com.rejeq.ktobs.request.general

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
data class GetStatsResponse(
    val cpuUsage: Double,
    val memoryUsage: Double,
    val availableDiskSpace: Double,
    val activeFps: Double,
    val averageFrameRenderTime: Double,
    val renderSkippedFrames: Int,
    val renderTotalFrames: Int,
    val outputSkippedFrames: Int,
    val outputTotalFrames: Int,
    val webSocketSessionIncomingMessages: Long,
    val webSocketSessionOutgoingMessages: Long,
)

// Gets statistics about OBS, obs-websocket, and the current session
suspend fun ObsSession.getStats(): GetStatsResponse = callMethod("GetStats")

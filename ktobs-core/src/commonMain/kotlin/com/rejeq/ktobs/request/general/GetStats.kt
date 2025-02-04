package com.rejeq.ktobs.request.general

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

/**
 * @property cpuUsage Current CPU usage in percent
 * @property memoryUsage Amount of memory in MB currently being used by OBS
 * @property availableDiskSpace Available disk space on the device being used
 *           for recording
 * @property activeFps Current FPS being rendered
 * @property averageFrameRenderTime Average time in milliseconds taken to render
 *           a frame
 * @property renderSkippedFrames Number of frames skipped in render thread
 * @property renderTotalFrames Total number of frames outputted by render thread
 * @property outputSkippedFrames Number of frames skipped by output thread
 * @property outputTotalFrames Total number of frames outputted by output thread
 * @property webSocketSessionIncomingMessages Total number of messages received
 *           by obs-websocket from the client
 * @property webSocketSessionOutgoingMessages Total number of messages sent by
 *           obs-websocket to the client
 */
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

/**
 * Gets statistics about OBS, obs-websocket, and the current session.
 */
suspend fun ObsSession.getStats(): GetStatsResponse = callMethod("GetStats")

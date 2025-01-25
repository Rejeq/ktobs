package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
data class VideoSettings(
    val fpsNumerator: Int,
    val fpsDenominator: Int,
    val baseWidth: Int,
    val baseHeight: Int,
    val outputWidth: Int,
    val outputHeight: Int,
)

// Gets the current video settings
suspend fun ObsSession.getVideoSettings(): VideoSettings =
    callMethod("GetVideoSettings")

package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import com.rejeq.ktobs.model.VideoSettings

/**
 * Gets the current video settings.
 *
 * Note: To get the true FPS value, divide the FPS numerator by the FPS
 * denominator. Example: 60000/1001
 */
suspend fun ObsSession.getVideoSettings(): VideoSettings =
    callMethod("GetVideoSettings")

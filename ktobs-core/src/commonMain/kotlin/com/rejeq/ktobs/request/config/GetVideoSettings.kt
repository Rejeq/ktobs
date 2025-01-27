package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import com.rejeq.ktobs.model.VideoSettings

// Gets the current video settings
suspend fun ObsSession.getVideoSettings(): VideoSettings =
    callMethod("GetVideoSettings")

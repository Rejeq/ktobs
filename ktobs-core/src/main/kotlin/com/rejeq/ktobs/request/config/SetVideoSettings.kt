package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class SetVideoSettingsRequest(
    val fpsNumerator: Int? = null,
    val fpsDenominator: Int? = null,
    val baseWidth: Int? = null,
    val baseHeight: Int? = null,
    val outputWidth: Int? = null,
    val outputHeight: Int? = null,
)

// Sets the current video settings
suspend fun ObsSession.setVideoSettings(
    fpsNumerator: Int? = null,
    fpsDenominator: Int? = null,
    baseWidth: Int? = null,
    baseHeight: Int? = null,
    outputWidth: Int? = null,
    outputHeight: Int? = null,
) = callUnitMethod(
    "SetVideoSettings",
    SetVideoSettingsRequest(
        fpsNumerator,
        fpsDenominator,
        baseWidth,
        baseHeight,
        outputWidth,
        outputHeight,
    ),
)

// Sets the current video settings
suspend fun ObsSession.setVideoSettings(settings: VideoSettings) =
    setVideoSettings(
        settings.fpsNumerator,
        settings.fpsDenominator,
        settings.baseWidth,
        settings.baseHeight,
        settings.outputWidth,
        settings.outputHeight,
    )

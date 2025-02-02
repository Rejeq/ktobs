package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import com.rejeq.ktobs.model.VideoSettings
import kotlinx.serialization.Serializable

@Serializable
class SetVideoSettingsRequest(
    val fpsNumerator: Int? = null,
    val fpsDenominator: Int? = null,
    val baseWidth: Int? = null,
    val baseHeight: Int? = null,
    val outputWidth: Int? = null,
    val outputHeight: Int? = null,
)

/**
 * Sets the current video settings.
 *
 * Note: Fields must be specified in pairs. For example, you cannot set only
 * baseWidth without needing to specify baseHeight.
 *
 * @param fpsNumerator Numerator of the fractional FPS value
 * @param fpsDenominator Denominator of the fractional FPS value
 * @param baseWidth Width of the base (canvas) resolution in pixels
 * @param baseHeight Height of the base (canvas) resolution in pixels
 * @param outputWidth Width of the output resolution in pixels
 * @param outputHeight Height of the output resolution in pixels
 */
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

/**
 * Sets the current video settings using a VideoSettings object.
 *
 * @param settings Video settings to apply
 */
suspend fun ObsSession.setVideoSettings(settings: VideoSettings) =
    setVideoSettings(
        settings.fpsNumerator,
        settings.fpsDenominator,
        settings.baseWidth,
        settings.baseHeight,
        settings.outputWidth,
        settings.outputHeight,
    )

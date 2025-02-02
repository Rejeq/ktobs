package com.rejeq.ktobs.model

import kotlinx.serialization.Serializable

/**
 * @property fpsNumerator Numerator of the fractional FPS value
 * @property fpsDenominator Denominator of the fractional FPS value
 * @property baseWidth Width of the base (canvas) resolution in pixels
 * @property baseHeight Height of the base (canvas) resolution in pixels
 * @property outputWidth Width of the output resolution in pixels
 * @property outputHeight Height of the output resolution in pixels
 */
@Serializable
data class VideoSettings(
    val fpsNumerator: Int,
    val fpsDenominator: Int,
    val baseWidth: Int,
    val baseHeight: Int,
    val outputWidth: Int,
    val outputHeight: Int,
)

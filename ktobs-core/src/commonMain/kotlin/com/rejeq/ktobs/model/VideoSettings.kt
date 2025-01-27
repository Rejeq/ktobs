package com.rejeq.ktobs.model

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

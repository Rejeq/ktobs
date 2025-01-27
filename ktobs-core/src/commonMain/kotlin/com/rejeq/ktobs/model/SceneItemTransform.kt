package com.rejeq.ktobs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object Alignment {
    const val CENTER = 0U
    const val LEFT = 1U
    const val RIGHT = 2U
    const val TOP = 4U
    const val BOTTOM = 8U
}

@Serializable
data class SceneItemTransform(
    @SerialName("sourceWidth") val sourceWidth: Float,
    @SerialName("sourceHeight") val sourceHeight: Float,
    @SerialName("positionX") val posX: Float,
    @SerialName("positionY") val posY: Float,
    @SerialName("rotation") val rotation: Float,
    @SerialName("scaleX") val scaleX: Float,
    @SerialName("scaleY") val scaleY: Float,
    @SerialName("width") val width: Float,
    @SerialName("height") val height: Float,
    @SerialName("alignment") val alignment: UInt,
    @SerialName("boundsType") val boundsType: BoundsType,
    @SerialName("boundsAlignment") val boundsAlignment: UInt,
    @SerialName("boundsWidth") val boundsWidth: Float,
    @SerialName("boundsHeight") val boundsHeight: Float,
    @SerialName("cropLeft") val cropLeft: UInt,
    @SerialName("cropRight") val cropRight: UInt,
    @SerialName("cropTop") val cropTop: UInt,
    @SerialName("cropBottom") val cropBottom: UInt,
    @SerialName("cropToBounds") val cropToBounds: Boolean? = null,
)

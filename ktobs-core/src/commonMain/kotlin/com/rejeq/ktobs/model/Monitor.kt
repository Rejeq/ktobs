package com.rejeq.ktobs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Monitor(
    @SerialName("monitorIndex") val index: Int,
    @SerialName("monitorName") val name: String,
    @SerialName("monitorWidth") val width: Int,
    @SerialName("monitorHeight") val height: Int,
    @SerialName("monitorPositionX") val positionX: Int,
    @SerialName("monitorPositionY") val positionY: Int,
)

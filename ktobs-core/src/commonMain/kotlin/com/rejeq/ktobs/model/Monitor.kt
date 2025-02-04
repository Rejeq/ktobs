package com.rejeq.ktobs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @property index Index of the monitor starting at 0
 * @property name Monitor name/ID
 * @property width Monitor resolution width
 * @property height Monitor resolution height
 * @property positionX Monitor position on the desktop x-coordinate
 * @property positionY Monitor position on the desktop y-coordinate
 */
@Serializable
data class Monitor(
    @SerialName("monitorIndex") val index: Int,
    @SerialName("monitorName") val name: String,
    @SerialName("monitorWidth") val width: Int,
    @SerialName("monitorHeight") val height: Int,
    @SerialName("monitorPositionX") val positionX: Int,
    @SerialName("monitorPositionY") val positionY: Int,
)

package com.rejeq.ktobs.request.ui

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
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

@Serializable
data class GetMonitorListResponse(
    val monitors: List<Monitor>,
)

// Gets a list of connected monitors and their properties
suspend fun ObsSession.getMonitorList(): List<Monitor> =
    callMethod<GetMonitorListResponse>("GetMonitorList").monitors

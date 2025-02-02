package com.rejeq.ktobs.request.ui

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import com.rejeq.ktobs.model.Monitor
import kotlinx.serialization.Serializable

@Serializable
class GetMonitorListResponse(
    val monitors: List<Monitor>,
)

// Gets a list of connected monitors and their properties
suspend fun ObsSession.getMonitorList(): List<Monitor> =
    callMethod<GetMonitorListResponse>("GetMonitorList").monitors

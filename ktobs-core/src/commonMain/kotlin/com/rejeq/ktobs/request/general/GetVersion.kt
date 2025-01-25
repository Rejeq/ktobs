package com.rejeq.ktobs.request.general

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
data class GetVersionResponse(
    val obsVersion: String,
    val obsWebSocketVersion: String,
    val rpcVersion: Int,
    val availableRequests: List<String>,
    val supportedImageFormats: List<String>,
    val platform: String,
    val platformDescription: String,
)

// Gets data about the current plugin and RPC version
suspend fun ObsSession.getVersion(): GetVersionResponse =
    callMethod("GetVersion")

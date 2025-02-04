package com.rejeq.ktobs.request.general

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

/**
 * @property obsVersion Current OBS Studio version
 * @property obsWebSocketVersion Current obs-websocket version
 * @property rpcVersion Current latest obs-websocket RPC version
 * @property availableRequests Array of available RPC requests for the currently
 *           negotiated RPC version
 * @property supportedImageFormats Image formats available in
 *           GetSourceScreenshot and SaveSourceScreenshot requests
 * @property platform Name of the platform. Usually windows, macos, or ubuntu
 *           (linux flavor). Not guaranteed to be any of those
 * @property platformDescription Description of the platform,
 *           like Windows 10 (10.0)
 */
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

/**
 * Gets data about the current plugin and RPC version.
 */
suspend fun ObsSession.getVersion(): GetVersionResponse =
    callMethod("GetVersion")

package com.rejeq.ktobs.request.ui

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetStudioModeEnabledResponse(
    @SerialName("studioModeEnabled") val enabled: Boolean,
)

// Gets whether studio mode is enabled
suspend fun ObsSession.getStudioModeEnabled(): Boolean =
    callMethod<GetStudioModeEnabledResponse>("GetStudioModeEnabled").enabled

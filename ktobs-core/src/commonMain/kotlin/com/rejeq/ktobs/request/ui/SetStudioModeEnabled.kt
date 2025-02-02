package com.rejeq.ktobs.request.ui

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SetStudioModeEnabledRequest(
    @SerialName("studioModeEnabled") val enabled: Boolean,
)

// Enables or disables studio mode
suspend fun ObsSession.setStudioModeEnabled(enabled: Boolean) =
    callUnitMethod("SetStudioModeEnabled", SetStudioModeEnabledRequest(enabled))

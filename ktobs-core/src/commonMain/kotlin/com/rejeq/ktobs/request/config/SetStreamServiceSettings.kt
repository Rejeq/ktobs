package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
class SetStreamServiceSettingsRequest(
    val streamServiceType: String,
    val streamServiceSettings: JsonElement,
)

// Sets the current stream service settings (stream destination)
suspend fun ObsSession.setStreamServiceSettings(
    type: String,
    settings: JsonElement,
) = callUnitMethod(
    "SetStreamServiceSettings",
    SetStreamServiceSettingsRequest(type, settings),
)

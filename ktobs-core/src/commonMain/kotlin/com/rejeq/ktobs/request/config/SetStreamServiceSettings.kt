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

/**
 * Sets the current stream service settings (stream destination).
 *
 * Note: Simple RTMP settings can be set with type rtmp_custom and the settings
 * fields server and key.
 *
 * @param type Type of stream service to apply. Example: rtmp_common or
 *        rtmp_custom
 * @param settings Settings to apply to the service
 */
suspend fun ObsSession.setStreamServiceSettings(
    type: String,
    settings: JsonElement,
) = callUnitMethod(
    "SetStreamServiceSettings",
    SetStreamServiceSettingsRequest(type, settings),
)

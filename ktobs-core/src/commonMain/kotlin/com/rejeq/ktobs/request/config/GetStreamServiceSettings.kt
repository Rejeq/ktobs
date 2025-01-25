package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class GetStreamServiceSettingsResponse(
    @SerialName("streamServiceType") val type: String,
    @SerialName("streamServiceSettings") val settings: JsonElement,
)

// Gets the current stream service settings (stream destination)
suspend fun ObsSession.getStreamServiceSettings() =
    callMethod<GetStreamServiceSettingsResponse>("GetStreamServiceSettings")

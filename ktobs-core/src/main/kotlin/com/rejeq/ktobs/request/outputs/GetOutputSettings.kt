package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class GetOutputSettingsRequest(
    val outputName: String,
)

@Serializable
data class GetOutputSettingsResponse(
    val outputSettings: JsonElement,
)

// Gets the settings of an output
suspend fun ObsSession.getOutputSettings(outputName: String): JsonElement =
    callMethod<GetOutputSettingsResponse, GetOutputSettingsRequest>(
        "GetOutputSettings",
        GetOutputSettingsRequest(outputName),
    ).outputSettings

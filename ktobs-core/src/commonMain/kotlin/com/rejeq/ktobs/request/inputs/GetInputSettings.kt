package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
class GetInputSettingsRequest(
    val inputName: String?,
    val inputUuid: String?,
)

@Serializable
data class GetInputSettingsResponse(
    @SerialName("inputKind") val kind: String,
    @SerialName("inputSettings") val settings: JsonElement,
)

// Gets the settings of an input
suspend fun ObsSession.getInputSettings(
    inputName: String? = null,
    inputUuid: String? = null,
): GetInputSettingsResponse =
    callMethod(
        "GetInputSettings",
        GetInputSettingsRequest(inputName, inputUuid),
    )

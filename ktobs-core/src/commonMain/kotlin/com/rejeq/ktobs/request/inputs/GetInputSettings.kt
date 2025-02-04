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

/**
 * @property kind The kind of the input
 * @property settings Object of settings for the input
 */
@Serializable
data class GetInputSettingsResponse(
    @SerialName("inputKind") val kind: String,
    @SerialName("inputSettings") val settings: JsonElement,
)

/**
 * Gets the settings of an input.
 *
 * Note: Does not include defaults. To create the entire settings object,
 * overlay inputSettings over the defaultInputSettings provided by
 * GetInputDefaultSettings.
 *
 * @param inputName Name of the input to get the settings of
 * @param inputUuid UUID of the input to get the settings of
 */
suspend fun ObsSession.getInputSettings(
    inputName: String? = null,
    inputUuid: String? = null,
): GetInputSettingsResponse =
    callMethod(
        "GetInputSettings",
        GetInputSettingsRequest(inputName, inputUuid),
    )

package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
class SetOutputSettingsRequest(
    val outputName: String,
    val outputSettings: JsonElement,
)

/**
 * Sets the settings of an output.
 *
 * @param name Name of the output to set the settings of
 * @param settings Settings to apply to the output
 */
suspend fun ObsSession.setOutputSettings(
    name: String,
    settings: JsonElement,
) = callUnitMethod(
    "SetOutputSettings",
    SetOutputSettingsRequest(name, settings),
)

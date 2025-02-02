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

// Sets the settings of an output
suspend fun ObsSession.setOutputSettings(
    name: String,
    settings: JsonElement,
) = callUnitMethod(
    "SetOutputSettings",
    SetOutputSettingsRequest(name, settings),
)

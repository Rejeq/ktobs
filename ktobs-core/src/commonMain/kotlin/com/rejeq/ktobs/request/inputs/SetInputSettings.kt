package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
class SetInputSettingsRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
    val inputSettings: JsonElement,
    val overlay: Boolean? = true,
)

/**
 * Sets the settings of an input.
 *
 * @param name Name of the input to set the settings of
 * @param settings Object of settings to apply
 * @param overlay True == apply the settings on top of existing ones,
 *        False == reset the input to its defaults, then apply settings
 */
suspend fun ObsSession.setInputSettings(
    name: String? = null,
    uuid: String? = null,
    settings: JsonElement,
    overlay: Boolean? = true,
) = callUnitMethod(
    "SetInputSettings",
    SetInputSettingsRequest(name, uuid, settings, overlay),
)

package com.rejeq.ktobs.request.transitions

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class SetCurrentSceneTransitionSettingsRequest(
    val transitionSettings: JsonElement,
    val overlay: Boolean?,
)

// Sets the settings of the current scene transition
suspend fun ObsSession.setCurrentSceneTransitionSettings(
    settings: JsonElement,
    overlay: Boolean? = null,
) = callUnitMethod(
    "SetCurrentSceneTransitionSettings",
    SetCurrentSceneTransitionSettingsRequest(settings, overlay),
)

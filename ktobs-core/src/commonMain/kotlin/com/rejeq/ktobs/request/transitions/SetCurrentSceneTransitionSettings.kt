package com.rejeq.ktobs.request.transitions

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
class SetCurrentSceneTransitionSettingsRequest(
    val transitionSettings: JsonElement,
    val overlay: Boolean?,
)

/**
 * Sets the settings of the current scene transition.
 *
 * @param settings Settings object to apply to the transition
 * @param overlay Whether to overlay over the current settings or replace them
 */
suspend fun ObsSession.setCurrentSceneTransitionSettings(
    settings: JsonElement,
    overlay: Boolean? = null,
) = callUnitMethod(
    "SetCurrentSceneTransitionSettings",
    SetCurrentSceneTransitionSettingsRequest(settings, overlay),
)

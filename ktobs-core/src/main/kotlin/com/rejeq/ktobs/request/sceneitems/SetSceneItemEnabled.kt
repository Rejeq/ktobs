package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class SetSceneItemEnabledRequest(
    val sceneName: String?,
    val sceneUuid: String?,
    val sceneItemId: Int,
    val sceneItemEnabled: Boolean,
)

// Sets the enable state of a scene item
// Scenes and Groups
suspend fun ObsSession.setSceneItemEnabled(
    sceneName: String? = null,
    sceneUuid: String? = null,
    sceneItemId: Int,
    enabled: Boolean,
) = callUnitMethod(
    "SetSceneItemEnabled",
    SetSceneItemEnabledRequest(sceneName, sceneUuid, sceneItemId, enabled),
)

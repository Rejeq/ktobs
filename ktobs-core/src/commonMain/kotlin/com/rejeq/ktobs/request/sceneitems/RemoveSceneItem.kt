package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class RemoveSceneItemRequest(
    val sceneName: String? = null,
    val sceneUuid: String? = null,
    val sceneItemId: Int,
)

// Removes a scene item from a scene
suspend fun ObsSession.removeSceneItem(
    sceneName: String? = null,
    sceneUuid: String? = null,
    itemId: Int,
) = callUnitMethod(
    "RemoveSceneItem",
    RemoveSceneItemRequest(sceneName, sceneUuid, itemId),
)

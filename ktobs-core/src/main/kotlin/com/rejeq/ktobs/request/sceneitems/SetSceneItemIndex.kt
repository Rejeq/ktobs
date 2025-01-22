package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class SetSceneItemIndexRequest(
    val sceneName: String?,
    val sceneUuid: String?,
    val sceneItemId: Int,
    val sceneItemIndex: Int,
)

// Sets the index position of a scene item in a scene
// Scenes and Groups
suspend fun ObsSession.setSceneItemIndex(
    sceneName: String? = null,
    sceneUuid: String? = null,
    id: Int,
    index: Int,
) = callUnitMethod(
    "SetSceneItemIndex",
    SetSceneItemIndexRequest(sceneName, sceneUuid, id, index),
)

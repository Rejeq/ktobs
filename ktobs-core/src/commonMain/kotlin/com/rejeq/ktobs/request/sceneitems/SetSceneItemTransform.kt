package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import com.rejeq.ktobs.model.SceneItemTransform
import kotlinx.serialization.Serializable

@Serializable
class SetSceneItemTransformRequest(
    val sceneName: String? = null,
    val sceneUuid: String? = null,
    val sceneItemId: Int,
    val sceneItemTransform: SceneItemTransform,
)

// Sets the transform and crop info of a scene item
suspend fun ObsSession.setSceneItemTransform(
    sceneName: String? = null,
    sceneUuid: String? = null,
    sceneItemId: Int,
    transform: SceneItemTransform,
) = callUnitMethod(
    "SetSceneItemTransform",
    SetSceneItemTransformRequest(sceneName, sceneUuid, sceneItemId, transform),
)

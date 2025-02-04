package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import com.rejeq.ktobs.model.SceneItemTransform
import kotlinx.serialization.Serializable

@Serializable
class GetSceneItemTransformRequest(
    val sceneName: String?,
    val sceneUuid: String?,
    val sceneItemId: Int,
)

@Serializable
class GetSceneItemTransformResponse(
    val sceneItemTransform: SceneItemTransform,
)

/**
 * Gets the transform and crop info of a scene item.
 *
 * @param sceneName Name of the scene the item is in
 * @param sceneUuid UUID of the scene the item is in
 * @param sceneItemId Numeric ID of the scene item
 * @return Object containing scene item transform info
 */
suspend fun ObsSession.getSceneItemTransform(
    sceneName: String? = null,
    sceneUuid: String? = null,
    sceneItemId: Int,
): SceneItemTransform =
    callMethod<GetSceneItemTransformResponse, GetSceneItemTransformRequest>(
        "GetSceneItemTransform",
        GetSceneItemTransformRequest(sceneName, sceneUuid, sceneItemId),
    ).sceneItemTransform

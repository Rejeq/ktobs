package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import com.rejeq.ktobs.model.SceneItem
import kotlinx.serialization.Serializable

@Serializable
class GetSceneItemListRequest(
    val sceneName: String?,
    val sceneUuid: String?,
)

@Serializable
class GetSceneItemListResponse(
    val sceneItems: List<SceneItem>,
)

/**
 * Gets a list of all scene items in a scene.
 *
 * @param sceneName Name of the scene to get the items of
 * @param sceneUuid UUID of the scene to get the items of
 * @return Array of scene items in the scene
 */
suspend fun ObsSession.getSceneItemList(
    sceneName: String? = null,
    sceneUuid: String? = null,
): List<SceneItem> =
    callMethod<GetSceneItemListResponse, GetSceneItemListRequest>(
        "GetSceneItemList",
        GetSceneItemListRequest(sceneName, sceneUuid),
    ).sceneItems

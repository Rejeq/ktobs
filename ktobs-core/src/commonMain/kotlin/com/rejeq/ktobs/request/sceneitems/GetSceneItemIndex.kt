package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
class GetSceneItemIndexRequest(
    val sceneName: String?,
    val sceneUuid: String?,
    val sceneItemId: Int,
)

@Serializable
class GetSceneItemIndexResponse(
    val sceneItemIndex: Int,
)

/**
 * Gets the index position of a scene item in a scene.
 *
 * An index of 0 is at the bottom of the source list in the UI.
 *
 * @param sceneName Name of the scene the item is in
 * @param sceneUuid UUID of the scene the item is in
 * @param sceneItemId Numeric ID of the scene item
 * @return Index position of the scene item
 */
suspend fun ObsSession.getSceneItemIndex(
    sceneName: String? = null,
    sceneUuid: String? = null,
    sceneItemId: Int,
): Int =
    callMethod<GetSceneItemIndexResponse, GetSceneItemIndexRequest>(
        "GetSceneItemIndex",
        GetSceneItemIndexRequest(sceneName, sceneUuid, sceneItemId),
    ).sceneItemIndex

package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
class GetSceneItemIdRequest(
    val sceneName: String?,
    val sceneUuid: String?,
    val sourceName: String,
    val searchOffset: Int?,
)

@Serializable
class GetSceneItemIdResponse(
    val sceneItemId: Int,
)

/**
 * Gets the ID of a source item in a scene.
 *
 * @param sceneName Name of the scene or group to get the item id of
 * @param sceneUuid UUID of the scene or group to get the item id of
 * @param sourceName Name of the source to get the item id of
 * @param searchOffset Number of matches to skip during search
 * @return Numeric ID of the scene item
 */
suspend fun ObsSession.getSceneItemId(
    sceneName: String? = null,
    sceneUuid: String? = null,
    sourceName: String,
    searchOffset: Int? = null,
): Int =
    callMethod<GetSceneItemIdResponse, GetSceneItemIdRequest>(
        "GetSceneItemId",
        GetSceneItemIdRequest(sceneName, sceneUuid, sourceName, searchOffset),
    ).sceneItemId

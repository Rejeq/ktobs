package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
class GetGroupSceneItemListRequest(
    val sceneName: String?,
    val sceneUuid: String?,
)

@Serializable
class GetGroupSceneItemListResponse(
    val sceneItems: List<JsonElement>,
)

/**
 * Gets a list of scene items in a group.
 * Basically GetSceneItemList, but for groups.
 *
 * Using groups at all in OBS is discouraged, as they are very broken under the
 * hood. Please use nested scenes instead.
 *
 * @param sceneName Name of the group to get the items of
 * @param sceneUuid UUID of the group to get the items of
 * @return Array of scene items in the group
 */
suspend fun ObsSession.getGroupSceneItemList(
    sceneName: String? = null,
    sceneUuid: String? = null,
): List<JsonElement> =
    callMethod<GetGroupSceneItemListResponse, GetGroupSceneItemListRequest>(
        "GetGroupSceneItemList",
        GetGroupSceneItemListRequest(sceneName, sceneUuid),
    ).sceneItems

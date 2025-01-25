package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class GetGroupSceneItemListRequest(
    val sceneName: String?,
    val sceneUuid: String?,
)

@Serializable
data class GetGroupSceneItemListResponse(
    val sceneItems: List<JsonElement>,
)

// Basically GetSceneItemList, but for groups
// Using groups at all in OBS is discouraged, as they are very broken under the
// hood. Please use nested scenes instead
suspend fun ObsSession.getGroupSceneItemList(
    sceneName: String? = null,
    sceneUuid: String? = null,
): List<JsonElement> =
    callMethod<GetGroupSceneItemListResponse, GetGroupSceneItemListRequest>(
        "GetGroupSceneItemList",
        GetGroupSceneItemListRequest(sceneName, sceneUuid),
    ).sceneItems

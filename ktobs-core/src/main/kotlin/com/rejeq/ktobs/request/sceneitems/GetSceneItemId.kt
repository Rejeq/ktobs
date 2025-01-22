package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
data class GetSceneItemIdRequest(
    val sceneName: String?,
    val sceneUuid: String?,
    val sourceName: String,
    val searchOffset: Int?,
)

@Serializable
data class GetSceneItemIdResponse(
    val sceneItemId: Int,
)

// Searches a scene for a source, and returns its id
// Scenes and Groups
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

package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
data class GetSceneItemEnabledRequest(
    val sceneName: String?,
    val sceneUuid: String?,
    val sceneItemId: Int,
)

@Serializable
data class GetSceneItemEnabledResponse(
    val sceneItemEnabled: Boolean,
)

// Gets the enable state of a scene item
// Scenes and Groups
suspend fun ObsSession.getSceneItemEnabled(
    sceneName: String? = null,
    sceneUuid: String? = null,
    sceneItemId: Int,
): Boolean =
    callMethod<GetSceneItemEnabledResponse, GetSceneItemEnabledRequest>(
        "GetSceneItemEnabled",
        GetSceneItemEnabledRequest(sceneName, sceneUuid, sceneItemId),
    ).sceneItemEnabled

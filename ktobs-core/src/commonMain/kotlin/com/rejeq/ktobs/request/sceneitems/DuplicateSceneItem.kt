package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
data class DuplicateSceneItemRequest(
    val sceneName: String? = null,
    val sceneUuid: String? = null,
    val sceneItemId: Int,
    val destinationSceneName: String? = null,
    val destinationSceneUuid: String? = null,
)

@Serializable
data class DuplicateSceneItemResponse(
    val sceneItemId: Int,
)

// Duplicates a scene item, copying all transform and crop info
// Scenes only
suspend fun ObsSession.duplicateSceneItem(
    sceneName: String? = null,
    sceneUuid: String? = null,
    sceneItemId: Int,
    destinationSceneName: String? = null,
    destinationSceneUuid: String? = null,
): Int =
    callMethod<DuplicateSceneItemResponse, DuplicateSceneItemRequest>(
        "DuplicateSceneItem",
        DuplicateSceneItemRequest(
            sceneName,
            sceneUuid,
            sceneItemId,
            destinationSceneName,
            destinationSceneUuid,
        ),
    ).sceneItemId

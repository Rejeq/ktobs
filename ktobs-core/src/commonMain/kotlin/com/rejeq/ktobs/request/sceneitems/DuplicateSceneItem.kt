package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
class DuplicateSceneItemRequest(
    val sceneName: String? = null,
    val sceneUuid: String? = null,
    val sceneItemId: Int,
    val destinationSceneName: String? = null,
    val destinationSceneUuid: String? = null,
)

@Serializable
class DuplicateSceneItemResponse(
    val sceneItemId: Int,
)

/**
 * Duplicates a scene item, copying all transform and crop info.
 *
 * @param sceneName Name of the scene the item is in
 * @param sceneUuid UUID of the scene the item is in
 * @param sceneItemId Numeric ID of the scene item
 * @param destinationSceneName Name of the scene to create the item in
 * @param destinationSceneUuid UUID of the scene to create the item in
 * @return Numeric ID of the duplicated scene item
 */
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

package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
class CreateSceneItemRequest(
    val sceneName: String? = null,
    val sceneUuid: String? = null,
    val sourceName: String? = null,
    val sourceUuid: String? = null,
    val sceneItemEnabled: Boolean? = null,
)

@Serializable
class CreateSceneItemResponse(
    val sceneItemId: Int,
)

/**
 * Creates a new scene item using a source.
 *
 * @param sceneName Name of the scene to create item in
 * @param sceneUuid UUID of the scene to create item in
 * @param sourceName Name of the source to add to the scene
 * @param sceneItemEnabled Whether to set the created scene item to enabled or
 *        disabled
 * @return ID of the scene item that was created
 */
suspend fun ObsSession.createSceneItem(
    sceneName: String? = null,
    sceneUuid: String? = null,
    sourceName: String? = null,
    sourceUuid: String? = null,
    enabled: Boolean? = null,
): Int =
    callMethod<CreateSceneItemResponse, CreateSceneItemRequest>(
        "CreateSceneItem",
        CreateSceneItemRequest(
            sceneName,
            sceneUuid,
            sourceName,
            sourceUuid,
            enabled,
        ),
    ).sceneItemId

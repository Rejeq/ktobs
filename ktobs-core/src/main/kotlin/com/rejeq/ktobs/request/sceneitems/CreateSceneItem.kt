package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
data class CreateSceneItemRequest(
    val sceneName: String? = null,
    val sceneUuid: String? = null,
    val sourceName: String? = null,
    val sourceUuid: String? = null,
    val sceneItemEnabled: Boolean? = null,
)

@Serializable
data class CreateSceneItemResponse(
    val sceneItemId: Int,
)

// Creates a new scene item using a source
// Scenes only
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

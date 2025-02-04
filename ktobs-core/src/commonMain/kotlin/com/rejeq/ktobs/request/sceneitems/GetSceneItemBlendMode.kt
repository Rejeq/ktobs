package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import com.rejeq.ktobs.model.BlendMode
import kotlinx.serialization.Serializable

@Serializable
class GetSceneItemBlendModeRequest(
    val sceneName: String?,
    val sceneUuid: String?,
    val sceneItemId: Int,
)

@Serializable
class GetSceneItemBlendModeResponse(
    val sceneItemBlendMode: BlendMode,
)

/**
 * Gets the blend mode of a scene item.
 *
 * @param sceneName Name of the scene the item is in
 * @param sceneUuid UUID of the scene the item is in
 * @param sceneItemId Numeric ID of the scene item
 * @return Current blend mode
 */
suspend fun ObsSession.getSceneItemBlendMode(
    sceneName: String? = null,
    sceneUuid: String? = null,
    sceneItemId: Int,
): BlendMode =
    callMethod<GetSceneItemBlendModeResponse, GetSceneItemBlendModeRequest>(
        "GetSceneItemBlendMode",
        GetSceneItemBlendModeRequest(sceneName, sceneUuid, sceneItemId),
    ).sceneItemBlendMode

package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
class GetSceneItemEnabledRequest(
    val sceneName: String?,
    val sceneUuid: String?,
    val sceneItemId: Int,
)

@Serializable
class GetSceneItemEnabledResponse(
    val sceneItemEnabled: Boolean,
)

/**
 * Gets the enable state of a scene item.
 *
 * @param sceneName Name of the scene the item is in
 * @param sceneUuid UUID of the scene the item is in
 * @param sceneItemId Numeric ID of the scene item
 * @return Whether the scene item is enabled
 */
suspend fun ObsSession.getSceneItemEnabled(
    sceneName: String? = null,
    sceneUuid: String? = null,
    sceneItemId: Int,
): Boolean =
    callMethod<GetSceneItemEnabledResponse, GetSceneItemEnabledRequest>(
        "GetSceneItemEnabled",
        GetSceneItemEnabledRequest(sceneName, sceneUuid, sceneItemId),
    ).sceneItemEnabled

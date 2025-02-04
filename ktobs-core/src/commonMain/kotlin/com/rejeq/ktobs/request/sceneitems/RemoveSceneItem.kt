package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class RemoveSceneItemRequest(
    val sceneName: String? = null,
    val sceneUuid: String? = null,
    val sceneItemId: Int,
)

/**
 * Removes a scene item from a scene.
 *
 * @param sceneName Name of the scene the item is in
 * @param sceneUuid UUID of the scene the item is in
 * @param sceneItemId Numeric ID of the scene item
 */
suspend fun ObsSession.removeSceneItem(
    sceneName: String? = null,
    sceneUuid: String? = null,
    itemId: Int,
) = callUnitMethod(
    "RemoveSceneItem",
    RemoveSceneItemRequest(sceneName, sceneUuid, itemId),
)

package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetSceneItemIndexRequest(
    val sceneName: String?,
    val sceneUuid: String?,
    val sceneItemId: Int,
    val sceneItemIndex: Int,
)

/**
 * Sets the index position of a scene item in a scene.
 *
 * @param sceneName Name of the scene the item is in
 * @param sceneUuid UUID of the scene the item is in
 * @param id Numeric ID of the scene item
 * @param index New index position of the scene item
 */
suspend fun ObsSession.setSceneItemIndex(
    sceneName: String? = null,
    sceneUuid: String? = null,
    id: Int,
    index: Int,
) = callUnitMethod(
    "SetSceneItemIndex",
    SetSceneItemIndexRequest(sceneName, sceneUuid, id, index),
)

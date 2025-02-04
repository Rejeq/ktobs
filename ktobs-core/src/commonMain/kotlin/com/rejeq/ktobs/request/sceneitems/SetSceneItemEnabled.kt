package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetSceneItemEnabledRequest(
    val sceneName: String?,
    val sceneUuid: String?,
    val sceneItemId: Int,
    val sceneItemEnabled: Boolean,
)

/**
 * Sets the enable state of a scene item.
 *
 * @param sceneName Name of the scene the item is in
 * @param sceneUuid UUID of the scene the item is in
 * @param sceneItemId Numeric ID of the scene item
 * @param sceneItemEnabled New enable state of the scene item
 */
suspend fun ObsSession.setSceneItemEnabled(
    sceneName: String? = null,
    sceneUuid: String? = null,
    sceneItemId: Int,
    enabled: Boolean,
) = callUnitMethod(
    "SetSceneItemEnabled",
    SetSceneItemEnabledRequest(sceneName, sceneUuid, sceneItemId, enabled),
)

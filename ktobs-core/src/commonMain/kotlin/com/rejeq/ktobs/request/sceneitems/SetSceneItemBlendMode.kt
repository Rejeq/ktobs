package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import com.rejeq.ktobs.model.BlendMode
import kotlinx.serialization.Serializable

@Serializable
class SetSceneItemBlendModeRequest(
    val sceneName: String?,
    val sceneUuid: String?,
    val sceneItemId: Int,
    val sceneItemBlendMode: BlendMode,
)

/**
 * Sets the blend mode of a scene item.
 *
 * @param sceneName Name of the scene the item is in
 * @param sceneUuid UUID of the scene the item is in
 * @param id Numeric ID of the scene item
 * @param mode New blend mode
 */
suspend fun ObsSession.setSceneItemBlendMode(
    sceneName: String? = null,
    sceneUuid: String? = null,
    id: Int,
    mode: BlendMode,
) = callUnitMethod(
    "SetSceneItemBlendMode",
    SetSceneItemBlendModeRequest(sceneName, sceneUuid, id, mode),
)

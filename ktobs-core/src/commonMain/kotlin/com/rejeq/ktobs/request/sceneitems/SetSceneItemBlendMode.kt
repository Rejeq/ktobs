package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import com.rejeq.ktobs.model.BlendMode
import kotlinx.serialization.Serializable

@Serializable
data class SetSceneItemBlendModeRequest(
    val sceneName: String?,
    val sceneUuid: String?,
    val sceneItemId: Int,
    val sceneItemBlendMode: BlendMode,
)

// Sets the blend mode of a scene item
// Scenes and Groups
suspend fun ObsSession.setSceneItemBlendMode(
    sceneName: String? = null,
    sceneUuid: String? = null,
    id: Int,
    mode: BlendMode,
) = callUnitMethod(
    "SetSceneItemBlendMode",
    SetSceneItemBlendModeRequest(sceneName, sceneUuid, id, mode),
)

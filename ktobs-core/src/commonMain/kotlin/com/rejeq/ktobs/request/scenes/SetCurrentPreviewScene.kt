package com.rejeq.ktobs.request.scenes

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class SetCurrentPreviewSceneRequest(
    val sceneName: String? = null,
    val sceneUuid: String? = null,
)

// Sets the current preview scene
// Only available when studio mode is enabled
suspend fun ObsSession.setCurrentPreviewScene(
    name: String? = null,
    uuid: String? = null,
) = callUnitMethod(
    "SetCurrentPreviewScene",
    SetCurrentPreviewSceneRequest(name, uuid),
)

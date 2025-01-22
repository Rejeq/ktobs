package com.rejeq.ktobs.request.scene

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class RemoveSceneRequest(
    val sceneName: String? = null,
    val sceneUuid: String? = null,
)

// Removes a scene from OBS
suspend fun ObsSession.removeScene(
    name: String? = null,
    uuid: String? = null,
) = callUnitMethod("RemoveScene", RemoveSceneRequest(name, uuid))

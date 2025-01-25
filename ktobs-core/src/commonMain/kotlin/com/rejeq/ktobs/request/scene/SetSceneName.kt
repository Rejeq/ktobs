package com.rejeq.ktobs.request.scene

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class SetSceneNameRequest(
    val sceneName: String? = null,
    val sceneUuid: String? = null,
    val newSceneName: String,
)

// Sets the name of a scene (rename)
suspend fun ObsSession.setSceneName(
    name: String? = null,
    uuid: String? = null,
    newName: String,
) = callUnitMethod(
    "SetSceneName",
    SetSceneNameRequest(name, uuid, newName),
)

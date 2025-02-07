package com.rejeq.ktobs.request.scenes

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetSceneNameRequest(
    val sceneName: String? = null,
    val sceneUuid: String? = null,
    val newSceneName: String,
)

/**
 * Sets the name of a scene (rename).
 *
 * @param name Name of the scene to rename
 * @param uuid UUID of the scene to rename
 * @param newName New name for the scene
 */
suspend fun ObsSession.setSceneName(
    name: String? = null,
    uuid: String? = null,
    newName: String,
) = callUnitMethod(
    "SetSceneName",
    SetSceneNameRequest(name, uuid, newName),
)

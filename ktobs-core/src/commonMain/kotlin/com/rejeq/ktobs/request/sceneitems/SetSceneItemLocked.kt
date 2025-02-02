package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetSceneItemLockedRequest(
    val sceneName: String?,
    val sceneUuid: String?,
    val sceneItemId: Int,
    val sceneItemLocked: Boolean,
)

// Sets the lock state of a scene item
// Scenes and Groups
suspend fun ObsSession.setSceneItemLocked(
    sceneName: String? = null,
    sceneUuid: String? = null,
    sceneItemId: Int,
    locked: Boolean,
) = callUnitMethod(
    "SetSceneItemLocked",
    SetSceneItemLockedRequest(sceneName, sceneUuid, sceneItemId, locked),
)

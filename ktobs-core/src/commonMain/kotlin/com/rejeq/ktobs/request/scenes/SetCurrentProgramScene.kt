package com.rejeq.ktobs.request.scenes

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetCurrentProgramSceneRequest(
    val sceneName: String? = null,
    val sceneUuid: String? = null,
)

// Sets the current program scene
suspend fun ObsSession.setCurrentProgramScene(
    name: String? = null,
    uuid: String? = null,
) = callUnitMethod(
    "SetCurrentProgramScene",
    SetCurrentProgramSceneRequest(name, uuid),
)

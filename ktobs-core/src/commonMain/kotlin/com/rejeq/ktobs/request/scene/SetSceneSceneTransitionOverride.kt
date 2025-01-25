package com.rejeq.ktobs.request.scene

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class SetSceneSceneTransitionOverrideRequest(
    val sceneName: String? = null,
    val sceneUuid: String? = null,
    val transitionName: String? = null,
    // Value are restricted from >= 50 and <= 20000
    val transitionDuration: Int? = null,
)

// Sets the scene transition override for a scene
suspend fun ObsSession.setSceneSceneTransitionOverride(
    sceneName: String? = null,
    sceneUuid: String? = null,
    transitionName: String? = null,
    transitionDuration: Int? = null,
) = callUnitMethod(
    "SetSceneSceneTransitionOverride",
    SetSceneSceneTransitionOverrideRequest(
        sceneName,
        sceneUuid,
        transitionName,
        transitionDuration,
    ),
)

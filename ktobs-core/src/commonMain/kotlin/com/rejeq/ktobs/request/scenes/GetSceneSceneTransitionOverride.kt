package com.rejeq.ktobs.request.scenes

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
data class GetSceneSceneTransitionOverrideRequest(
    val sceneName: String? = null,
    val sceneUuid: String? = null,
)

@Serializable
data class GetSceneSceneTransitionOverrideResponse(
    val transitionName: String?,
    val transitionDuration: Int?,
)

// Gets the scene transition overridden for a scene
suspend fun ObsSession.getSceneSceneTransitionOverride(
    sceneName: String? = null,
    sceneUuid: String? = null,
): GetSceneSceneTransitionOverrideResponse =
    callMethod(
        "GetSceneSceneTransitionOverride",
        GetSceneSceneTransitionOverrideRequest(sceneName, sceneUuid),
    )

package com.rejeq.ktobs.request.scene

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
data class CreateSceneRequest(
    val sceneName: String,
)

@Serializable
data class CreateSceneResponse(
    val sceneUuid: String,
)

// Creates a new scene in OBS
suspend fun ObsSession.createScene(name: String): String =
    callMethod<CreateSceneResponse, CreateSceneRequest>(
        "CreateScene",
        CreateSceneRequest(name),
    ).sceneUuid

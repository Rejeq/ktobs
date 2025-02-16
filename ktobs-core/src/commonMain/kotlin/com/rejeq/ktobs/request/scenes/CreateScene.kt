package com.rejeq.ktobs.request.scenes

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
class CreateSceneRequest(
    val sceneName: String,
)

@Serializable
class CreateSceneResponse(
    val sceneUuid: String,
)

/**
 * Creates a new scene in OBS.
 *
 * @param name Name for the new scene
 * @return UUID of the created scene or null if OBS WebSocket version is
 *         less than 5.4.0
 */
suspend fun ObsSession.createScene(name: String): String? =
    callMethod<CreateSceneResponse?, CreateSceneRequest>(
        "CreateScene",
        CreateSceneRequest(name),
    )?.sceneUuid

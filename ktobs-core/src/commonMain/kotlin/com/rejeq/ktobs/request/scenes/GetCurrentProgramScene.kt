package com.rejeq.ktobs.request.scenes

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property name Curent program scene name
 * @property uuid Current program scene UUID
 * @property curProgramName Current program scene name (Deprecated)
 * @property curProgramUuid Current program scene UUID (Deprecated)
 */
@Serializable
data class GetCurrentProgramSceneResponse(
    @SerialName("sceneName") val name: String,
    @SerialName("sceneUuid") val uuid: String,
    @SerialName("currentProgramSceneName") val currProgramName: String,
    @SerialName("currentProgramSceneUuid") val currProgramUuid: String,
)

/**
 * Gets the current program scene.
 *
 * @return Name of the current program scene
 */
suspend fun ObsSession.getCurrentProgramScene():
    GetCurrentProgramSceneResponse =
    callMethod("GetCurrentProgramScene")

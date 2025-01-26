package com.rejeq.ktobs.request.scenes

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCurrentProgramSceneResponse(
    @SerialName("sceneName") val name: String,
    @SerialName("sceneUuid") val uuid: String,
    @SerialName("currentProgramSceneName") val currProgramName: String,
    @SerialName("currentProgramSceneUuid") val currProgramUuid: String,
)

// Gets the current program scene
suspend fun ObsSession.getCurrentProgramScene():
    GetCurrentProgramSceneResponse =
    callMethod("GetCurrentProgramScene")

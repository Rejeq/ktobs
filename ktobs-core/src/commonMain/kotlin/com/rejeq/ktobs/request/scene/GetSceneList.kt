package com.rejeq.ktobs.request.scene

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Scene(
    @SerialName("sceneName") val name: String,
    @SerialName("sceneUuid") val uuid: String,
    @SerialName("sceneIndex") val index: Int,
)

@Serializable
data class GetSceneListResponse(
    @SerialName("currentProgramSceneName") val programName: String?,
    @SerialName("currentProgramSceneUuid") val programUuid: String?,
    @SerialName("currentPreviewSceneName") val previewName: String?,
    @SerialName("currentPreviewSceneUuid") val previewUuid: String?,
    val scenes: List<Scene>,
)

// Gets an array of all scenes in OBS
suspend fun ObsSession.getSceneList(): GetSceneListResponse =
    callMethod("GetSceneList")

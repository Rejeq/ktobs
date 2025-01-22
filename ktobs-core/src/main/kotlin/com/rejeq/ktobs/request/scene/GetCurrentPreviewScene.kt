package com.rejeq.ktobs.request.scene

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCurrentPreviewSceneResponse(
    @SerialName("sceneName") val name: String,
    @SerialName("sceneUuid") val uuid: String,
    @SerialName("currentPreviewSceneName") val currPreviewName: String? = null,
    @SerialName("currentPreviewSceneUuid") val currPreviewUuid: String? = null,
)

// Gets the current preview scene
// Only available when studio mode is enabled
suspend fun ObsSession.getCurrentPreviewScene():
    GetCurrentPreviewSceneResponse =
    callMethod("GetCurrentPreviewScene")

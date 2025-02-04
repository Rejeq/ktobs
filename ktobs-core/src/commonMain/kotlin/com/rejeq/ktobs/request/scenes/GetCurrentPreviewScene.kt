package com.rejeq.ktobs.request.scenes

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property name Current preview scene name
 * @property uuid Current preview scene UUID
 * @property currPreviewName Current preview scene name
 * @property currPreviewUuid Current preview scene UUID
 */
@Serializable
data class GetCurrentPreviewSceneResponse(
    @SerialName("sceneName") val name: String,
    @SerialName("sceneUuid") val uuid: String,
    @SerialName("currentPreviewSceneName") val currPreviewName: String? = null,
    @SerialName("currentPreviewSceneUuid") val currPreviewUuid: String? = null,
)

/**
 * Gets the current preview scene.
 *
 * Only available when studio mode is enabled.
 *
 * @return Name of the current preview scene
 */
suspend fun ObsSession.getCurrentPreviewScene():
    GetCurrentPreviewSceneResponse =
    callMethod("GetCurrentPreviewScene")

package com.rejeq.ktobs.request.scenes

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import com.rejeq.ktobs.model.Scene
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property programName Current program scene name.
 *           Can be null if internal state desync.
 *           Or if OBS WebSocket version is less than 5.4.0
 * @property programUuid Current program scene UUID.
 *           Can be null if internal state desync.
 *           Or if OBS WebSocket version is less than 5.4.0
 * @property previewName Current preview scene name.
 *           Can be null if not in studio mode.
 *           Or if OBS WebSocket version is less than 5.4.0
 * @property previewUuid Current preview scene UUID.
 *           Can be null if not in studio mode.
 *           Or if OBS WebSocket version is less than 5.4.0
 * @property scenes Array of scenes
 */
@Serializable
data class GetSceneListResponse(
    @SerialName("currentProgramSceneName") val programName: String? = null,
    @SerialName("currentProgramSceneUuid") val programUuid: String? = null,
    @SerialName("currentPreviewSceneName") val previewName: String? = null,
    @SerialName("currentPreviewSceneUuid") val previewUuid: String? = null,
    val scenes: List<Scene>,
)

/**
 * Gets an array of all scenes in OBS.
 */
suspend fun ObsSession.getSceneList(): GetSceneListResponse =
    callMethod("GetSceneList")

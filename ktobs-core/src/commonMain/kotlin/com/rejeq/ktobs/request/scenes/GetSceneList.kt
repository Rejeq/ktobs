package com.rejeq.ktobs.request.scenes

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import com.rejeq.ktobs.model.Scene
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property programName Current program scene name.
 *           Can be null if internal state desync
 * @property programUuid Current program scene UUID.
 *           Can be null if internal state desync
 * @property previewName Current preview scene name. nul if not in studio mode
 * @property previewUuid Current preview scene UUID. null if not in studio mode
 * @property scenes Array of scenes
 */
@Serializable
data class GetSceneListResponse(
    @SerialName("currentProgramSceneName") val programName: String?,
    @SerialName("currentProgramSceneUuid") val programUuid: String?,
    @SerialName("currentPreviewSceneName") val previewName: String?,
    @SerialName("currentPreviewSceneUuid") val previewUuid: String?,
    val scenes: List<Scene>,
)

/**
 * Gets an array of all scenes in OBS.
 */
suspend fun ObsSession.getSceneList(): GetSceneListResponse =
    callMethod("GetSceneList")

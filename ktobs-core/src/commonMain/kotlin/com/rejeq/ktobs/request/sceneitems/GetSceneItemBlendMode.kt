package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class BlendMode {
    @SerialName("OBS_BLEND_NORMAL")
    Normal,

    @SerialName("OBS_BLEND_ADDITIVE")
    Additive,

    @SerialName("OBS_BLEND_SUBTRACT")
    Subtract,

    @SerialName("OBS_BLEND_SCREEN")
    Screen,

    @SerialName("OBS_BLEND_MULTIPLY")
    Multiply,

    @SerialName("OBS_BLEND_LIGHTEN")
    Lighten,

    @SerialName("OBS_BLEND_DARKEN")
    Darken,
}

@Serializable
data class GetSceneItemBlendModeRequest(
    val sceneName: String?,
    val sceneUuid: String?,
    val sceneItemId: Int,
)

@Serializable
data class GetSceneItemBlendModeResponse(
    val sceneItemBlendMode: BlendMode,
)

// Gets the blend mode of a scene item
// Scenes and Groups
suspend fun ObsSession.getSceneItemBlendMode(
    sceneName: String? = null,
    sceneUuid: String? = null,
    sceneItemId: Int,
): BlendMode =
    callMethod<GetSceneItemBlendModeResponse, GetSceneItemBlendModeRequest>(
        "GetSceneItemBlendMode",
        GetSceneItemBlendModeRequest(sceneName, sceneUuid, sceneItemId),
    ).sceneItemBlendMode

package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSceneItemListRequest(
    val sceneName: String?,
    val sceneUuid: String?,
)

@Serializable
enum class SourceType {
    @SerialName("OBS_SOURCE_TYPE_INPUT")
    Input,

    @SerialName("OBS_SOURCE_TYPE_FILTER")
    Filter,

    @SerialName("OBS_SOURCE_TYPE_TRANSITION")
    Transition,

    @SerialName("OBS_SOURCE_TYPE_SCENE")
    Scene,
}

@Serializable
enum class BoundsType {
    @SerialName("OBS_BOUNDS_NONE")
    None,

    @SerialName("OBS_BOUNDS_STRETCH")
    Stretch,

    @SerialName("OBS_BOUNDS_SCALE_INNER")
    ScaleInner,

    @SerialName("OBS_BOUNDS_SCALE_OUTER")
    ScaleOuter,

    @SerialName("OBS_BOUNDS_SCALE_TO_WIDTH")
    ScaleToWidth,

    @SerialName("OBS_BOUNDS_SCALE_TO_HEIGHT")
    ScaleToHeight,

    @SerialName("OBS_BOUNDS_MAX_ONLY")
    MaxOnly,
}

@Serializable
data class SceneItem(
    @SerialName("sceneItemId") val id: Int,
    @SerialName("sceneItemIndex") val index: Int,
    @SerialName("sceneItemEnabled") val enabled: Boolean,
    @SerialName("sceneItemLocked") val locked: Boolean,
    @SerialName("sceneItemTransform") val transform: SceneItemTransform,
    @SerialName("sceneItemBlendMode") val blendMode: BlendMode,
    @SerialName("sourceName") val sourceName: String,
    @SerialName("sourceUuid") val sourceUuid: String,
    @SerialName("sourceType") val sourceType: SourceType,
    @SerialName("inputKind") val inputKind: String?,
    @SerialName("isGroup") val isGroup: Boolean?,
)

@Serializable
data class GetSceneItemListResponse(
    val sceneItems: List<SceneItem>,
)

// Gets a list of all scene items in a scene
suspend fun ObsSession.getSceneItemList(
    sceneName: String? = null,
    sceneUuid: String? = null,
): List<SceneItem> =
    callMethod<GetSceneItemListResponse, GetSceneItemListRequest>(
        "GetSceneItemList",
        GetSceneItemListRequest(sceneName, sceneUuid),
    ).sceneItems

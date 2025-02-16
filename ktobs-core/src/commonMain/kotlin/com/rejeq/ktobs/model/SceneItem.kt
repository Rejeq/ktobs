package com.rejeq.ktobs.model

import com.rejeq.ktobs.model.SceneItemTransform
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SceneItem(
    @SerialName("sceneItemId") val id: Int,
    @SerialName("sceneItemIndex") val index: Int,
    @SerialName("sceneItemEnabled") val enabled: Boolean,
    @SerialName("sceneItemLocked") val locked: Boolean,
    @SerialName("sceneItemTransform") val transform: SceneItemTransform,
    @SerialName("sceneItemBlendMode") val blendMode: BlendMode,
    @SerialName("sourceName") val sourceName: String,
    @SerialName("sourceUuid") val sourceUuid: String? = null,
    @SerialName("sourceType") val sourceType: SourceType,
    @SerialName("inputKind") val inputKind: String?,
    @SerialName("isGroup") val isGroup: Boolean?,
)

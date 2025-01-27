package com.rejeq.ktobs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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

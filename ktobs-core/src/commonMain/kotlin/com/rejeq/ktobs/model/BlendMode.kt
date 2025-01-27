package com.rejeq.ktobs.model

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

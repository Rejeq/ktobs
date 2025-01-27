package com.rejeq.ktobs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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

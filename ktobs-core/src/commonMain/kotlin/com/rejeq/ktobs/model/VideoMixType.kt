package com.rejeq.ktobs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class VideoMixType {
    @SerialName("OBS_WEBSOCKET_VIDEO_MIX_TYPE_PREVIEW")
    Preview,

    @SerialName("OBS_WEBSOCKET_VIDEO_MIX_TYPE_PROGRAM")
    Program,

    @SerialName("OBS_WEBSOCKET_VIDEO_MIX_TYPE_MULTIVIEW")
    Multiview,
}

package com.rejeq.ktobs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MediaAction {
    @SerialName("OBS_WEBSOCKET_MEDIA_INPUT_ACTION_NONE")
    NONE,

    @SerialName("OBS_WEBSOCKET_MEDIA_INPUT_ACTION_PLAY")
    Play,

    @SerialName("OBS_WEBSOCKET_MEDIA_INPUT_ACTION_PAUSE")
    Pause,

    @SerialName("OBS_WEBSOCKET_MEDIA_INPUT_ACTION_STOP")
    Stop,

    @SerialName("OBS_WEBSOCKET_MEDIA_INPUT_ACTION_RESTART")
    Restart,

    @SerialName("OBS_WEBSOCKET_MEDIA_INPUT_ACTION_NEXT")
    Next,

    @SerialName("OBS_WEBSOCKET_MEDIA_INPUT_ACTION_PREVIOUS")
    Previous,
}

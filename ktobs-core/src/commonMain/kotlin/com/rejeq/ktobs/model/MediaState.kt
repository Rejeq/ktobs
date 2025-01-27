package com.rejeq.ktobs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MediaState {
    @SerialName("OBS_MEDIA_STATE_NONE")
    None,

    @SerialName("OBS_MEDIA_STATE_PLAYING")
    Playing,

    @SerialName("MEDIA_STATE_OPENING")
    Opening,

    @SerialName("OBS_MEDIA_STATE_BUFFERING")
    Buffering,

    @SerialName("OBS_MEDIA_STATE_PAUSED")
    Paused,

    @SerialName("OBS_MEDIA_STATE_STOPPED")
    Stopped,

    @SerialName("OBS_MEDIA_STATE_ENDED")
    Ended,

    @SerialName("OBS_MEDIA_STATE_ERROR")
    Error,
}

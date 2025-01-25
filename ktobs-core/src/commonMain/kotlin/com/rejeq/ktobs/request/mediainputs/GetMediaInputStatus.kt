package com.rejeq.ktobs.request.mediainputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
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

@Serializable
data class GetMediaInputStatusRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
)

@Serializable
data class GetMediaInputStatusResponse(
    val mediaState: MediaState,
    val mediaDuration: Long?,
    val mediaCursor: Long?,
)

// Gets the status of a media input
suspend fun ObsSession.getMediaInputStatus(
    inputName: String? = null,
    inputUuid: String? = null,
): GetMediaInputStatusResponse =
    callMethod(
        "GetMediaInputStatus",
        GetMediaInputStatusRequest(inputName, inputUuid),
    )

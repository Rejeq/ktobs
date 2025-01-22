package com.rejeq.ktobs.request.mediainputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
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

@Serializable
data class TriggerMediaInputActionRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
    val mediaAction: MediaAction,
)

// Triggers an action on a media input
suspend fun ObsSession.triggerMediaInputAction(
    inputName: String? = null,
    inputUuid: String? = null,
    mediaAction: MediaAction,
) = callUnitMethod(
    "TriggerMediaInputAction",
    TriggerMediaInputActionRequest(inputName, inputUuid, mediaAction),
)

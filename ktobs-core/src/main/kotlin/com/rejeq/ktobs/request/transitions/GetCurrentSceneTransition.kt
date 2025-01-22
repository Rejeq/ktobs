package com.rejeq.ktobs.request.transitions

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class GetCurrentSceneTransitionResponse(
    @SerialName("transitionName") val name: String,
    @SerialName("transitionUuid") val uuid: String,
    @SerialName("transitionKind") val kind: String,
    @SerialName("transitionFixed") val fixed: Boolean,
    @SerialName("transitionDuration") val duration: Int?,
    @SerialName("transitionSettings") val settings: JsonElement?,
    @SerialName("transitionConfigurable") val configurable: Boolean,
)

// Gets information about the current scene transition
suspend fun ObsSession.getCurrentSceneTransition():
    GetCurrentSceneTransitionResponse =
    callMethod("GetCurrentSceneTransition")

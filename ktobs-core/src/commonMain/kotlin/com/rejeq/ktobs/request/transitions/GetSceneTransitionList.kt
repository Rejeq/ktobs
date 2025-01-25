package com.rejeq.ktobs.request.transitions

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Transition(
    @SerialName("transitionName") val name: String,
    @SerialName("transitionUuid") val uuid: String,
    @SerialName("transitionKind") val kind: String,
    @SerialName("transitionFixed") val fixed: Boolean,
    @SerialName("transitionDuration") val duration: Int? = null,
    @SerialName("transitionConfigurable") val configurable: Boolean,
)

@Serializable
data class GetSceneTransitionListResponse(
    @SerialName("currentSceneTransitionName") val currentName: String,
    @SerialName("currentSceneTransitionUuid") val currentUuid: String,
    @SerialName("currentSceneTransitionKind") val currentKind: String,
    @SerialName("transitions") val transitions: List<Transition>,
)

// Gets an array of all scene transitions in OBS
suspend fun ObsSession.getSceneTransitionList():
    GetSceneTransitionListResponse =
    callMethod("GetSceneTransitionList")

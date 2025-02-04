package com.rejeq.ktobs.request.transitions

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import com.rejeq.ktobs.model.Transition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property currentName Name of the current scene transition
 * @property currentUuid UUID of the current scene transition
 * @property currentKind Kind of the current scene transition
 * @property transitions Array of transitions
 */
@Serializable
data class GetSceneTransitionListResponse(
    @SerialName("currentSceneTransitionName") val currentName: String?,
    @SerialName("currentSceneTransitionUuid") val currentUuid: String?,
    @SerialName("currentSceneTransitionKind") val currentKind: String?,
    @SerialName("transitions") val transitions: List<Transition>,
)

/**
 * Gets an array of all scene transitions in OBS.
 */
suspend fun ObsSession.getSceneTransitionList():
    GetSceneTransitionListResponse =
    callMethod("GetSceneTransitionList")

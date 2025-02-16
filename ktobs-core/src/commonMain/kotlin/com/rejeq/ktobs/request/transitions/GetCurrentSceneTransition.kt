package com.rejeq.ktobs.request.transitions

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/**
 * @property name Name of the transition
 * @property uuid UUID of the transition
 *           Can be null if OBS WebSocket version is less than 5.4.0
 * @property kind Kind of the transition
 * @property fixed Whether the transition uses a fixed (unconfigurable) duration
 * @property duration Duration in milliseconds of the transition.
 *           null if transition is fixed
 * @property settings Object of settings for the transition.
 *           null if transition is not configurable
 * @property configurable Whether the transition supports being configured
 */
@Serializable
data class GetCurrentSceneTransitionResponse(
    @SerialName("transitionName") val name: String,
    @SerialName("transitionUuid") val uuid: String? = null,
    @SerialName("transitionKind") val kind: String,
    @SerialName("transitionFixed") val fixed: Boolean,
    @SerialName("transitionDuration") val duration: Int?,
    @SerialName("transitionSettings") val settings: JsonElement?,
    @SerialName("transitionConfigurable") val configurable: Boolean,
)

/**
 * Gets information about the current scene transition.
 */
suspend fun ObsSession.getCurrentSceneTransition():
    GetCurrentSceneTransitionResponse =
    callMethod("GetCurrentSceneTransition")

package com.rejeq.ktobs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property name Name of the transition
 * @property uuid Name of the transition
 *           Can be null if OBS WebSocket version is less than 5.4.0
 * @property kind Kind of the transition
 * @property fixed Whether the transition uses a fixed (unconfigurable) duration
 * @property duration Configured transition duration in milliseconds. null if
 *           transition is fixed
 * @property configurable Whether the transition supports being configured
 **/
@Serializable
data class Transition(
    @SerialName("transitionName") val name: String,
    @SerialName("transitionUuid") val uuid: String? = null,
    @SerialName("transitionKind") val kind: String,
    @SerialName("transitionFixed") val fixed: Boolean,
    @SerialName("transitionDuration") val duration: Int? = null,
    @SerialName("transitionConfigurable") val configurable: Boolean,
)

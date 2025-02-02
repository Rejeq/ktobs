package com.rejeq.ktobs.model

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

package com.rejeq.ktobs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property name Name of the input
 * @property uuid UUID of the input
 *           This is available starting from OBS WebSocket version 5.4.0.
 *           It will be `null` for earlier versions.
 * @property kind The kind of the input
 * @property unversionedKind The unversioned kind of input (aka no `_v2` stuff)
 **/
@Serializable
data class Input(
    @SerialName("inputName") val name: String,
    @SerialName("inputUuid") val uuid: String? = null,
    @SerialName("inputKind") val kind: String,
    @SerialName("unversionedInputKind") val unversionedKind: String,
)

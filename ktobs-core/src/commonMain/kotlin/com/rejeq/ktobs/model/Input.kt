package com.rejeq.ktobs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Input(
    @SerialName("inputName") val name: String,
    @SerialName("inputUuid") val uuid: String,
    @SerialName("inputKind") val kind: String,
    @SerialName("unversionedInputKind") val unversionedKind: String,
)

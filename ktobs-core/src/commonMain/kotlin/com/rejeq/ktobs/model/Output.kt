package com.rejeq.ktobs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Output(
    @SerialName("outputName") val name: String,
    @SerialName("outputKind") val kind: String,
    @SerialName("outputWidth") val width: Int,
    @SerialName("outputHeight") val height: Int,
    @SerialName("outputActive") val active: Boolean,
    @SerialName("outputFlags") val flags: Map<String, Boolean>,
)

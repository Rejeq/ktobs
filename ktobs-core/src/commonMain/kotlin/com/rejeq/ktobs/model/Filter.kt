package com.rejeq.ktobs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Filter(
    @SerialName("filterName") val name: String,
    @SerialName("filterKind") val kind: String,
    @SerialName("filterIndex") val index: Int,
    @SerialName("filterEnabled") val enabled: Boolean,
    @SerialName("filterSettings") val settings: JsonElement,
)

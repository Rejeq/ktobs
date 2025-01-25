package com.rejeq.ktobs.request.filters

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class GetSourceFilterRequest(
    val sourceName: String? = null,
    val sourceUuid: String? = null,
    val filterName: String,
)

@Serializable
data class GetSourceFilterResponse(
    @SerialName("filterEnabled") val enabled: Boolean,
    @SerialName("filterIndex") val index: Int,
    @SerialName("filterKind") val kind: String,
    @SerialName("filterSettings") val settings: JsonElement,
)

// Gets the info for a specific source filter
suspend fun ObsSession.getSourceFilter(
    sourceName: String? = null,
    sourceUuid: String? = null,
    filterName: String,
): GetSourceFilterResponse =
    callMethod(
        "GetSourceFilter",
        GetSourceFilterRequest(sourceName, sourceUuid, filterName),
    )

package com.rejeq.ktobs.request.filters

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class GetSourceFilterListRequest(
    val sourceName: String? = null,
    val sourceUuid: String? = null,
)

@Serializable
data class Filter(
    @SerialName("filterName") val name: String,
    @SerialName("filterKind") val kind: String,
    @SerialName("filterIndex") val index: Int,
    @SerialName("filterEnabled") val enabled: Boolean,
    @SerialName("filterSettings") val settings: JsonElement,
)

@Serializable
data class GetSourceFilterListResponse(
    val filters: List<Filter>,
)

// Gets an array of all of a source's filters
suspend fun ObsSession.getSourceFilterList(
    sourceName: String? = null,
    sourceUuid: String? = null,
): List<Filter> =
    callMethod<
        GetSourceFilterListResponse,
        GetSourceFilterListRequest,
    >(
        "GetSourceFilterList",
        GetSourceFilterListRequest(sourceName, sourceUuid),
    ).filters

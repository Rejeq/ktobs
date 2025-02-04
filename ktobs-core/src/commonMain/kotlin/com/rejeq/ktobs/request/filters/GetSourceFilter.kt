package com.rejeq.ktobs.request.filters

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
class GetSourceFilterRequest(
    val sourceName: String? = null,
    val sourceUuid: String? = null,
    val filterName: String,
)

/**
 * @property enabled Whether the filter is enabled
 * @property index Index of the filter in the list, beginning at 0
 * @property kind The kind of filter
 * @property settings Settings object associated with the filter
 */
@Serializable
data class GetSourceFilterResponse(
    @SerialName("filterEnabled") val enabled: Boolean,
    @SerialName("filterIndex") val index: Int,
    @SerialName("filterKind") val kind: String,
    @SerialName("filterSettings") val settings: JsonElement,
)

/**
 * Gets the info for a specific source filter.
 *
 * @param sourceName Name of the source
 * @param sourceUuid UUID of the source
 * @param filterName Name of the filter
 */
suspend fun ObsSession.getSourceFilter(
    sourceName: String? = null,
    sourceUuid: String? = null,
    filterName: String,
): GetSourceFilterResponse =
    callMethod(
        "GetSourceFilter",
        GetSourceFilterRequest(sourceName, sourceUuid, filterName),
    )

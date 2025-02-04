package com.rejeq.ktobs.request.filters

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import com.rejeq.ktobs.model.Filter
import kotlinx.serialization.Serializable

@Serializable
class GetSourceFilterListRequest(
    val sourceName: String? = null,
    val sourceUuid: String? = null,
)

@Serializable
class GetSourceFilterListResponse(
    val filters: List<Filter>,
)

/**
 * Gets an array of all of a source's filters.
 *
 * @param sourceName Name of the source
 * @param sourceUuid UUID of the source
 * @return Array of filters
 */
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

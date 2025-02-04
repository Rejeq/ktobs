package com.rejeq.ktobs.request.filters

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class RemoveSourceFilterRequest(
    val sourceName: String? = null,
    val sourceUuid: String? = null,
    val filterName: String,
)

/**
 * Removes a filter from a source.
 *
 * @param sourceName Name of the source the filter is on
 * @param sourceUuid UUID of the source the filter is on
 * @param filterName Name of the filter to remove
 */
suspend fun ObsSession.removeSourceFilter(
    sourceName: String? = null,
    sourceUuid: String? = null,
    filterName: String,
) = callUnitMethod(
    "RemoveSourceFilter",
    RemoveSourceFilterRequest(sourceName, sourceUuid, filterName),
)

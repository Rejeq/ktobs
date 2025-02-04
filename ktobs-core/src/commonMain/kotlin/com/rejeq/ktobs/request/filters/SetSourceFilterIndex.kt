package com.rejeq.ktobs.request.filters

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetSourceFilterIndexRequest(
    val sourceName: String? = null,
    val sourceUuid: String? = null,
    val filterName: String,
    val filterIndex: Int,
)

/**
 * Sets the index position of a filter on a source.
 *
 * @param sourceName Name of the source the filter is on
 * @param sourceUuid UUID of the source the filter is on
 * @param filterName Name of the filter
 * @param filterIndex New index position of the filter
 */
suspend fun ObsSession.setSourceFilterIndex(
    sourceName: String? = null,
    sourceUuid: String? = null,
    filterName: String,
    index: Int,
) = callUnitMethod(
    "SetSourceFilterIndex",
    SetSourceFilterIndexRequest(sourceName, sourceUuid, filterName, index),
)

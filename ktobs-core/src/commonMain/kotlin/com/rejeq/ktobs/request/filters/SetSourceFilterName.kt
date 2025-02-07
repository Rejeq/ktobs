package com.rejeq.ktobs.request.filters

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetSourceFilterNameRequest(
    val sourceName: String? = null,
    val sourceUuid: String? = null,
    val filterName: String,
    val newFilterName: String,
)

/**
 * Sets the name of a source filter (rename).
 *
 * @param sourceName Name of the source the filter is on
 * @param sourceUuid UUID of the source the filter is on
 * @param filterName Current name of the filter
 * @param newName New name for the filter
 */
suspend fun ObsSession.setSourceFilterName(
    sourceName: String? = null,
    sourceUuid: String? = null,
    filterName: String,
    newName: String,
) = callUnitMethod(
    "SetSourceFilterName",
    SetSourceFilterNameRequest(sourceName, sourceUuid, filterName, newName),
)

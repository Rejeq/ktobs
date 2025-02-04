package com.rejeq.ktobs.request.filters

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
class CreateSourceFilterRequest(
    val sourceName: String? = null,
    val sourceUuid: String? = null,
    val filterName: String,
    val filterKind: String,
    val filterSettings: JsonElement? = null,
)

/**
 * Creates a new filter, adding it to the specified source.
 *
 * @param sourceName Name of the source to add the filter to
 * @param sourceUuid UUID of the source to add the filter to
 * @param filterName Name of the new filter to be created
 * @param filterKind The kind of filter to be created
 * @param filterSettings Settings object to initialize the filter with, set to
 *        null if you want to use it as default
 */
suspend fun ObsSession.createSourceFilter(
    sourceName: String? = null,
    sourceUuid: String? = null,
    filterName: String,
    filterKind: String,
    filterSettings: JsonElement? = null,
) = callUnitMethod(
    "CreateSourceFilter",
    CreateSourceFilterRequest(
        sourceName,
        sourceUuid,
        filterName,
        filterKind,
        filterSettings,
    ),
)

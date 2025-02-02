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

// Creates a new filter for a source
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

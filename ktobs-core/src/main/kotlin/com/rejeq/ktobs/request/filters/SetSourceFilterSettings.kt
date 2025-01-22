package com.rejeq.ktobs.request.filters

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class SetSourceFilterSettingsRequest(
    val sourceName: String? = null,
    val sourceUuid: String? = null,
    val filterName: String,
    val filterSettings: JsonElement,
    val overlay: Boolean? = null,
)

// Sets the settings of a source filter
suspend fun ObsSession.setSourceFilterSettings(
    sourceName: String? = null,
    sourceUuid: String? = null,
    filterName: String,
    settings: JsonElement,
    overlay: Boolean? = null,
) = callUnitMethod(
    "SetSourceFilterSettings",
    SetSourceFilterSettingsRequest(
        sourceName,
        sourceUuid,
        filterName,
        settings,
        overlay,
    ),
)

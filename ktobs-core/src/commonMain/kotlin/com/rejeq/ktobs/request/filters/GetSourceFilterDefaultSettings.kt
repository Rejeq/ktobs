package com.rejeq.ktobs.request.filters

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
class GetSourceFilterDefaultSettingsRequest(
    val filterKind: String,
)

@Serializable
class GetSourceFilterDefaultSettingsResponse(
    @SerialName("defaultFilterSettings") val settings: JsonElement,
)

// Gets the default settings for a filter kind
suspend fun ObsSession.getSourceFilterDefaultSettings(
    filterKind: String,
): JsonElement =
    callMethod<
        GetSourceFilterDefaultSettingsResponse,
        GetSourceFilterDefaultSettingsRequest,
    >(
        "GetSourceFilterDefaultSettings",
        GetSourceFilterDefaultSettingsRequest(filterKind),
    ).settings

package com.rejeq.ktobs.request.filters

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
class SetSourceFilterSettingsRequest(
    val sourceName: String? = null,
    val sourceUuid: String? = null,
    val filterName: String,
    val filterSettings: JsonElement,
    val overlay: Boolean? = null,
)

/**
 * Sets the settings of a source filter.
 *
 * @param sourceName Name of the source the filter is on
 * @param sourceUuid UUID of the source the filter is on
 * @param filterName Name of the filter to set the settings of
 * @param settings Object of settings to apply
 * @param overlay True == apply the settings on top of existing ones,
 *        False == reset the input to its defaults, then apply settings
 */
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

package com.rejeq.ktobs.request.filters

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetSourceFilterEnabledRequest(
    val sourceName: String? = null,
    val sourceUuid: String? = null,
    val filterName: String,
    val filterEnabled: Boolean,
)

// Sets the enable state of a source filter
suspend fun ObsSession.setSourceFilterEnabled(
    sourceName: String? = null,
    sourceUuid: String? = null,
    filterName: String,
    enabled: Boolean,
) = callUnitMethod(
    "SetSourceFilterEnabled",
    SetSourceFilterEnabledRequest(sourceName, sourceUuid, filterName, enabled),
)

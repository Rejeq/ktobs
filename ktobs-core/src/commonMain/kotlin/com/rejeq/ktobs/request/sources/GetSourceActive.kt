package com.rejeq.ktobs.request.sources

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
data class GetSourceActiveRequest(
    val sourceName: String?,
    val sourceUuid: String?,
)

@Serializable
data class GetSourceActiveResponse(
    val videoActive: Boolean,
    val videoShowing: Boolean,
)

// Gets the active and show state of a source
// Compatible with inputs and scenes
suspend fun ObsSession.getSourceActive(
    sourceName: String? = null,
    sourceUuid: String? = null,
): GetSourceActiveResponse =
    callMethod(
        "GetSourceActive",
        GetSourceActiveRequest(sourceName, sourceUuid),
    )

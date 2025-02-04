package com.rejeq.ktobs.request.sources

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
class GetSourceActiveRequest(
    val sourceName: String?,
    val sourceUuid: String?,
)

/**
 * @property videoActive Whether the source is showing in Program
 * @property videoShowing Whether the source is showing in the UI (Preview,
 *           Projector, Properties)
 */
@Serializable
data class GetSourceActiveResponse(
    val videoActive: Boolean,
    val videoShowing: Boolean,
)

/**
 * Gets the active state of a source.
 *
 * A source is considered active if it is being shown on the preview or program
 * view.
 *
 * @param sourceName Name of the source to get the active state of
 * @param sourceUuid UUID of the source to get the active state of
 * @return Whether the source is currently active
 */
suspend fun ObsSession.getSourceActive(
    sourceName: String? = null,
    sourceUuid: String? = null,
): GetSourceActiveResponse =
    callMethod(
        "GetSourceActive",
        GetSourceActiveRequest(sourceName, sourceUuid),
    )

package com.rejeq.ktobs.request.record

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ToggleRecordResponse(
    @SerialName("outputActive") val active: Boolean,
)

/**
 * Toggles the status of the record output.
 *
 * @return The new active state of the output
 */
suspend fun ObsSession.toggleRecord(): Boolean =
    callMethod<ToggleRecordResponse>("ToggleRecord").active

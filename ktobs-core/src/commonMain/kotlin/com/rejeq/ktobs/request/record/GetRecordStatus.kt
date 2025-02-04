package com.rejeq.ktobs.request.record

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

/**
 * @property outputActive Whether the output is active
 * @property outputPaused Whether the output is paused
 * @property outputTimecode Current formatted timecode string for the output
 * @property outputDuration Current duration in milliseconds for the output
 * @property outputBytes Number of bytes sent by the output
 */
@Serializable
data class GetRecordStatusResponse(
    val outputActive: Boolean,
    val outputPaused: Boolean,
    val outputTimecode: String,
    val outputDuration: Long,
    val outputBytes: Long,
)

/**
 * Gets the status of the record output.
 */
suspend fun ObsSession.getRecordStatus(): GetRecordStatusResponse =
    callMethod("GetRecordStatus")

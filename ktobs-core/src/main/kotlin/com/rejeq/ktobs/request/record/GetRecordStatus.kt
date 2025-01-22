package com.rejeq.ktobs.request.record

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
data class GetRecordStatusResponse(
    val outputActive: Boolean,
    val outputPaused: Boolean,
    val outputTimecode: String,
    val outputDuration: Long,
    val outputBytes: Long,
)

// Gets the status of the record output
suspend fun ObsSession.getRecordStatus(): GetRecordStatusResponse =
    callMethod("GetRecordStatus") 

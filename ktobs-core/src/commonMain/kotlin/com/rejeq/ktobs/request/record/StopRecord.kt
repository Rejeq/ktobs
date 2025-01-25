package com.rejeq.ktobs.request.record

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
data class StopRecordResponse(
    val outputPath: String,
)

// Stops the record output
suspend fun ObsSession.stopRecord(): String =
    callMethod<StopRecordResponse>("StopRecord").outputPath 

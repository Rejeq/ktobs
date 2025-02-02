package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
class GetRecordDirectoryResponse(
    val recordDirectory: String,
)

/**
 * Gets the current directory that the record output is set to.
 *
 * @return Output directory
 */
suspend fun ObsSession.getRecordDirectory(): String =
    callMethod<GetRecordDirectoryResponse>("GetRecordDirectory").recordDirectory

package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetRecordDirectoryRequest(
    val recordDirectory: String,
)

/**
 * Sets the current directory that the record output writes files to.
 *
 * @param directory Output directory
 */
suspend fun ObsSession.setRecordDirectory(directory: String) =
    callUnitMethod("SetRecordDirectory", SetRecordDirectoryRequest(directory))

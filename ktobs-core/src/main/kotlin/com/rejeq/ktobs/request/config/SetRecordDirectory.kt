package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class SetRecordDirectoryRequest(
    val recordDirectory: String,
)

// Sets the current directory that the record output writes files to
suspend fun ObsSession.setRecordDirectory(directory: String) =
    callUnitMethod("SetRecordDirectory", SetRecordDirectoryRequest(directory))

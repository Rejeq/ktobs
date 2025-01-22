package com.rejeq.ktobs.request.record

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class CreateRecordChapterRequest(
    val chapterName: String? = null,
)

// Creates a new chapter marker in the recording
suspend fun ObsSession.createRecordChapter(chapterName: String? = null) =
    callUnitMethod(
        "CreateRecordChapter",
        CreateRecordChapterRequest(chapterName),
    )

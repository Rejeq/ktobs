package com.rejeq.ktobs.request.record

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class CreateRecordChapterRequest(
    val chapterName: String? = null,
)

/**
 * Creates a new chapter in the current recording.
 */
suspend fun ObsSession.createRecordChapter(chapterName: String? = null) =
    callUnitMethod(
        "CreateRecordChapter",
        CreateRecordChapterRequest(chapterName),
    )

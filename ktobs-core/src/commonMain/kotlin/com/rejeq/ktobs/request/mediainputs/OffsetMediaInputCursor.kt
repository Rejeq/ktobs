package com.rejeq.ktobs.request.mediainputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class OffsetMediaInputCursorRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
    val mediaCursorOffset: Long,
)

// Offsets the cursor position of a media input by the specified value
// This request does not perform bounds checking of the cursor position.
suspend fun ObsSession.offsetMediaInputCursor(
    inputName: String? = null,
    inputUuid: String? = null,
    mediaCursorOffset: Long,
) = callUnitMethod(
    "OffsetMediaInputCursor",
    OffsetMediaInputCursorRequest(inputName, inputUuid, mediaCursorOffset),
)

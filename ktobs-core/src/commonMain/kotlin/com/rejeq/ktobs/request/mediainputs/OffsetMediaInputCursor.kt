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

/**
 * Offsets the current cursor position of a media input by the specified amount.
 *
 * Note: This request does not perform bounds checking of the cursor position.
 *
 * @param inputName Name of the media input
 * @param inputUuid UUID of the media input
 * @param mediaCursorOffset Value to add to the current cursor position
 */
suspend fun ObsSession.offsetMediaInputCursor(
    inputName: String? = null,
    inputUuid: String? = null,
    mediaCursorOffset: Long,
) = callUnitMethod(
    "OffsetMediaInputCursor",
    OffsetMediaInputCursorRequest(inputName, inputUuid, mediaCursorOffset),
)

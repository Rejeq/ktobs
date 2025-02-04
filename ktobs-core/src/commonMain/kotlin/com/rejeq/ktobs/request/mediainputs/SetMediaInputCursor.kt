package com.rejeq.ktobs.request.mediainputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetMediaInputCursorRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
    val mediaCursor: Long,
)

/**
 * Sets the cursor position of a media input.
 *
 * Note: This request does not perform bounds checking of the cursor position.
 *
 * @param inputName Name of the media input
 * @param inputUuid UUID of the media input
 * @param mediaCursor New cursor position to set
 */
suspend fun ObsSession.setMediaInputCursor(
    inputName: String? = null,
    inputUuid: String? = null,
    mediaCursor: Long,
) = callUnitMethod(
    "SetMediaInputCursor",
    SetMediaInputCursorRequest(inputName, inputUuid, mediaCursor),
)

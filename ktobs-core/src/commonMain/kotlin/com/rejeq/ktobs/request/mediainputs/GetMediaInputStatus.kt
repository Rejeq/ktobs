package com.rejeq.ktobs.request.mediainputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import com.rejeq.ktobs.model.MediaState
import kotlinx.serialization.Serializable

@Serializable
class GetMediaInputStatusRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
)

/**
 * @property mediaState State of the media input
 * @property mediaDuration Total duration of the playing media in milliseconds.
 *           null if not playing
 * @property mediaCursor Position of the cursor in milliseconds. null if not
 *           playing
 */
@Serializable
data class GetMediaInputStatusResponse(
    val mediaState: MediaState,
    val mediaDuration: Long?,
    val mediaCursor: Long?,
)

/**
 * Gets the status of a media input.
 *
 * @param inputName Name of the media input
 * @param inputUuid UUID of the media input
 */
suspend fun ObsSession.getMediaInputStatus(
    inputName: String? = null,
    inputUuid: String? = null,
): GetMediaInputStatusResponse =
    callMethod(
        "GetMediaInputStatus",
        GetMediaInputStatusRequest(inputName, inputUuid),
    )

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

@Serializable
data class GetMediaInputStatusResponse(
    val mediaState: MediaState,
    val mediaDuration: Long?,
    val mediaCursor: Long?,
)

// Gets the status of a media input
suspend fun ObsSession.getMediaInputStatus(
    inputName: String? = null,
    inputUuid: String? = null,
): GetMediaInputStatusResponse =
    callMethod(
        "GetMediaInputStatus",
        GetMediaInputStatusRequest(inputName, inputUuid),
    )

package com.rejeq.ktobs.request.stream

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendStreamCaptionRequest(
    @SerialName("captionText") val text: String,
)

// Sends CEA-608 caption text over the stream output
suspend fun ObsSession.sendStreamCaption(text: String) =
    callUnitMethod("SendStreamCaption", SendStreamCaptionRequest(text))

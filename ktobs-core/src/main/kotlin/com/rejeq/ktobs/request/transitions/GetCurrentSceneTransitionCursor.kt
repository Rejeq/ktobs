package com.rejeq.ktobs.request.transitions

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class GetCurrentSceneTransitionCursorResponse(
    val transitionCursor: JsonElement,
)

// Gets the cursor position of the current scene transition if supported
suspend fun ObsSession.getCurrentSceneTransitionCursor(): JsonElement =
    callMethod<GetCurrentSceneTransitionCursorResponse>(
        "GetCurrentSceneTransitionCursor",
    ).transitionCursor

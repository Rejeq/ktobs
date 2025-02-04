package com.rejeq.ktobs.request.transitions

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
class GetCurrentSceneTransitionCursorResponse(
    val transitionCursor: Double,
)

/**
 * Gets the cursor position of the current scene transition.
 *
 * @return Cursor position, between 0.0 and 1.0
 */
suspend fun ObsSession.getCurrentSceneTransitionCursor(): Double =
    callMethod<GetCurrentSceneTransitionCursorResponse>(
        "GetCurrentSceneTransitionCursor",
    ).transitionCursor

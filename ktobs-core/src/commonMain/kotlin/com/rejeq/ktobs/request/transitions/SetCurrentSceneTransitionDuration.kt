package com.rejeq.ktobs.request.transitions

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetCurrentSceneTransitionDurationRequest(
    val transitionDuration: Int,
)

// Sets the duration of the current scene transition, if it is not fixed
suspend fun ObsSession.setCurrentSceneTransitionDuration(duration: Int) =
    callUnitMethod(
        "SetCurrentSceneTransitionDuration",
        SetCurrentSceneTransitionDurationRequest(duration),
    )

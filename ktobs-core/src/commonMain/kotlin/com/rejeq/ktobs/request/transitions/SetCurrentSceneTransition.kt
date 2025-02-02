package com.rejeq.ktobs.request.transitions

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetCurrentSceneTransitionRequest(
    val transitionName: String,
)

// Sets the current scene transition
suspend fun ObsSession.setCurrentSceneTransition(name: String) =
    callUnitMethod(
        "SetCurrentSceneTransition",
        SetCurrentSceneTransitionRequest(name),
    )

package com.rejeq.ktobs.request.transitions

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetCurrentSceneTransitionRequest(
    val transitionName: String,
)

/**
 * Sets the current scene transition.
 *
 * While the namespace of scene transitions is generally unique, that uniqueness
 * is not a guarantee as it is with other resources like inputs.
 *
 * @param name Name of the transition to make active
 */
suspend fun ObsSession.setCurrentSceneTransition(name: String) =
    callUnitMethod(
        "SetCurrentSceneTransition",
        SetCurrentSceneTransitionRequest(name),
    )

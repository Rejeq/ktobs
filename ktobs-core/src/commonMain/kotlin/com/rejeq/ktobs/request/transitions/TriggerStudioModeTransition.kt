package com.rejeq.ktobs.request.transitions

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod

/**
 * Triggers the current scene transition. Same functionality as the Transition
 * button in studio mode.
 */
suspend fun ObsSession.triggerStudioModeTransition() =
    callUnitMethod("TriggerStudioModeTransition")

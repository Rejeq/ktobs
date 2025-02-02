package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetCurrentProfileRequest(
    val profileName: String,
)

/**
 * Switches to a profile.
 *
 * @param name Name of the profile to switch to
 */
suspend fun ObsSession.setCurrentProfile(name: String) =
    callUnitMethod("SetCurrentProfile", SetCurrentProfileRequest(name))

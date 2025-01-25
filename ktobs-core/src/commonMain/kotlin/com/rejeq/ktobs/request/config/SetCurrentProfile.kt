package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class SetCurrentProfileRequest(
    val profileName: String,
)

// Switches to a profile
suspend fun ObsSession.setCurrentProfile(name: String) =
    callUnitMethod("SetCurrentProfile", SetCurrentProfileRequest(name))

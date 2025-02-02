package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class RemoveProfileRequest(
    val profileName: String,
)

// Removes a profile. If the current profile is chosen, it will change to a
// different profile first
suspend fun ObsSession.removeProfile(name: String) =
    callUnitMethod("RemoveProfile", RemoveProfileRequest(name))

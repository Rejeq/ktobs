package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class CreateProfileRequest(
    val profileName: String,
)

// Creates a new profile, switching to it in the process
suspend fun ObsSession.createProfile(name: String) =
    callUnitMethod("CreateProfile", CreateProfileRequest(name))

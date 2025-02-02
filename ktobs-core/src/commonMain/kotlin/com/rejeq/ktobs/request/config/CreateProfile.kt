package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class CreateProfileRequest(
    val profileName: String,
)

/**
 * Creates a new profile, switching to it in the process.
 *
 * @param name Name for the new profile
 */
suspend fun ObsSession.createProfile(name: String) =
    callUnitMethod("CreateProfile", CreateProfileRequest(name))

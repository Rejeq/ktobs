package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
data class GetProfileListResponse(
    /** The name of the current profile */
    val currentProfileName: String,
    /** Array of all available profiles */
    val profiles: List<String>,
)

/**
 * Gets an array of all profiles
 */
suspend fun ObsSession.getProfileList(): GetProfileListResponse =
    callMethod("GetProfileList")

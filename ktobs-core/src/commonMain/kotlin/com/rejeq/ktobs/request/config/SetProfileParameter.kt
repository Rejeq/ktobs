package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetProfileParameterRequest(
    val parameterCategory: String,
    val parameterName: String,
    val parameterValue: String,
)

// Gets an array of all profiles
suspend fun ObsSession.setProfileParameter(
    category: String,
    name: String,
    value: String,
) = callUnitMethod(
    "SetProfileParameter",
    SetProfileParameterRequest(category, name, value),
)

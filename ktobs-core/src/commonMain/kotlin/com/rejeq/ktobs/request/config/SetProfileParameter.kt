package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetProfileParameterRequest(
    val parameterCategory: String,
    val parameterName: String,
    val parameterValue: String?,
)

/**
 * Sets the value of a parameter in the current profile's configuration.
 *
 * @param category Category of the parameter to set
 * @param name Name of the parameter to set
 * @param value Value of the parameter to set. Use null to delete
 */
suspend fun ObsSession.setProfileParameter(
    category: String,
    name: String,
    value: String?,
) = callUnitMethod(
    "SetProfileParameter",
    SetProfileParameterRequest(category, name, value),
)

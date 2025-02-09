package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GetProfileParameterRequest(
    val parameterCategory: String,
    val parameterName: String,
)

/**
 * @property value Value associated with the parameter. null if not set and no
 *           default
 * @property default Default value associated with the parameter. null if no
 *           default
 */
@Serializable
data class GetProfileParameterResponse(
    @SerialName("parameterValue") val value: String?,
    @SerialName("defaultParameterValue") val default: String?,
)

/**
 * Gets a parameter from the current profile's configuration.
 *
 * @param category Category of the parameter to get
 * @param name Name of the parameter to get
 */
suspend fun ObsSession.getProfileParameter(
    category: String,
    name: String,
): GetProfileParameterResponse =
    callMethod(
        "GetProfileParameter",
        GetProfileParameterRequest(category, name),
    )

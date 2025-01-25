package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetProfileParameterRequest(
    val parameterCategory: String,
    val parameterName: String,
)

@Serializable
data class GetProfileParameterResponse(
    @SerialName("parameterValue") val value: String,
    @SerialName("defaultParameterValue") val default: String,
)

// Gets a parameter from the current profile's configuration
suspend fun ObsSession.getProfileParameter(
    category: String,
    name: String,
): GetProfileParameterResponse =
    callMethod(
        "GetProfileParameter",
        GetProfileParameterRequest(category, name),
    )

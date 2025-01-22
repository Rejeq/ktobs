package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class PressInputPropertiesButtonRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
    val propertyName: String,
)

// Presses a button in the properties of an input
suspend fun ObsSession.pressInputPropertiesButton(
    inputName: String? = null,
    inputUuid: String? = null,
    propertyName: String,
) = callUnitMethod(
    "PressInputPropertiesButton",
    PressInputPropertiesButtonRequest(inputName, inputUuid, propertyName),
)

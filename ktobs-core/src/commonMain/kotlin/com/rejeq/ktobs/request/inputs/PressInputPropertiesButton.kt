package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class PressInputPropertiesButtonRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
    val propertyName: String,
)

/**
 * Presses a button in the properties of an input.
 *
 *
 * Some known propertyName values are:
 * - refreshnocache - Browser source reload button
 *
 * Note: Use this in cases where there is a button in the properties of an input
 * that cannot be accessed in any other way. For example, browser sources, where
 * there is a refresh button.
 *
 * @param inputName Name of the input
 * @param inputUuid UUID of the input
 * @param propertyName Name of the button property to press
 */
suspend fun ObsSession.pressInputPropertiesButton(
    inputName: String? = null,
    inputUuid: String? = null,
    propertyName: String,
) = callUnitMethod(
    "PressInputPropertiesButton",
    PressInputPropertiesButtonRequest(inputName, inputUuid, propertyName),
)

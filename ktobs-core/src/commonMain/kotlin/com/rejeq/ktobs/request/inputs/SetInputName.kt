package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetInputNameRequest(
    val inputName: String?,
    val inputUuid: String?,
    val newInputName: String,
)

/**
 * Sets the name of an input (rename).
 *
 * @param inputName Current input name
 * @param inputUuid Current input UUID
 * @param newInputName New name for the input
 */
suspend fun ObsSession.setInputName(
    inputName: String? = null,
    inputUuid: String? = null,
    newInputName: String,
) = callUnitMethod(
    "SetInputName",
    SetInputNameRequest(inputName, inputUuid, newInputName),
)

package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class RemoveInputRequest(
    val inputName: String?,
    val inputUuid: String?,
)

// Removes an existing input
suspend fun ObsSession.removeInput(
    inputName: String? = null,
    inputUuid: String? = null,
) = callUnitMethod("RemoveInput", RemoveInputRequest(inputName, inputUuid))

package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetInputAudioBalanceRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
    val inputAudioBalance: Double,
)

/**
 * Sets the audio balance of an input.
 *
 * @param name Name of the input to set the audio balance of
 * @param uuid UUID of the input to set the audio balance of
 * @param balance New audio balance value
 */
suspend fun ObsSession.setInputAudioBalance(
    name: String? = null,
    uuid: String? = null,
    balance: Double,
) = callUnitMethod(
    "SetInputAudioBalance",
    SetInputAudioBalanceRequest(name, uuid, balance),
)

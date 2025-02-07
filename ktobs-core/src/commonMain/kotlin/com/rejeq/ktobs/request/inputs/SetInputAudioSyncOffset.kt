package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetInputAudioSyncOffsetRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
    val inputAudioSyncOffset: Int,
)

/**
 * Sets the audio sync offset of an input.
 *
 * @param name Name of the input to set the audio sync offset of
 * @param uuid UUID of the input to set the audio sync offset of
 * @param offset New audio sync offset in milliseconds
 */
suspend fun ObsSession.setInputAudioSyncOffset(
    name: String? = null,
    uuid: String? = null,
    offset: Int,
) = callUnitMethod(
    "SetInputAudioSyncOffset",
    SetInputAudioSyncOffsetRequest(name, uuid, offset),
)

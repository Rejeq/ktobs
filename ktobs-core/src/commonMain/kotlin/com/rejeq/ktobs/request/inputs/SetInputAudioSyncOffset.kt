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

// Sets the audio sync offset of an input
suspend fun ObsSession.setInputAudioSyncOffset(
    name: String? = null,
    uuid: String? = null,
    offset: Int,
) = callUnitMethod(
    "SetInputAudioSyncOffset",
    SetInputAudioSyncOffsetRequest(name, uuid, offset),
)

package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class SetInputAudioMonitorTypeRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
    val monitorType: MonitorType,
)

// Sets the audio monitor type of an input
suspend fun ObsSession.setInputAudioMonitorType(
    name: String? = null,
    uuid: String? = null,
    type: MonitorType,
) = callUnitMethod(
    "SetInputAudioMonitorType",
    SetInputAudioMonitorTypeRequest(name, uuid, type),
)

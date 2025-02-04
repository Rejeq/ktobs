package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import com.rejeq.ktobs.model.MonitorType
import kotlinx.serialization.Serializable

@Serializable
class SetInputAudioMonitorTypeRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
    val monitorType: MonitorType,
)

/**
 * Sets the audio monitor type of an input.
 *
 * @param name Name of the input to set the audio monitor type of
 * @param uuid UUID of the input to set the audio monitor type of
 * @param type Audio monitor type
 */
suspend fun ObsSession.setInputAudioMonitorType(
    name: String? = null,
    uuid: String? = null,
    type: MonitorType,
) = callUnitMethod(
    "SetInputAudioMonitorType",
    SetInputAudioMonitorTypeRequest(name, uuid, type),
)

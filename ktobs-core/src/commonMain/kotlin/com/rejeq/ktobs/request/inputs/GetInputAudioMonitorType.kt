package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import com.rejeq.ktobs.model.MonitorType
import kotlinx.serialization.Serializable

@Serializable
data class GetInputAudioMonitorTypeRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
)

@Serializable
data class GetInputAudioMonitorTypeResponse(
    val monitorType: MonitorType,
)

// Gets the audio monitor type of input
suspend fun ObsSession.getInputAudioMonitorType(
    inputName: String? = null,
    inputUuid: String? = null,
): MonitorType =
    callMethod<
        GetInputAudioMonitorTypeResponse,
        GetInputAudioMonitorTypeRequest,
    >(
        "GetInputAudioMonitorType",
        GetInputAudioMonitorTypeRequest(inputName, inputUuid),
    ).monitorType

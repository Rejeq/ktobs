package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetInputAudioMonitorTypeRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
)

@Serializable
enum class MonitorType {
    @SerialName("OBS_MONITORING_TYPE_NONE")
    None,

    @SerialName("OBS_MONITORING_TYPE_MONITOR_ONLY")
    MonitorOnly,

    @SerialName("OBS_MONITORING_TYPE_MONITOR_AND_OUTPUT")
    MonitorAndOutput,
}

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

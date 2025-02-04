package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import com.rejeq.ktobs.model.MonitorType
import kotlinx.serialization.Serializable

@Serializable
class GetInputAudioMonitorTypeRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
)

@Serializable
class GetInputAudioMonitorTypeResponse(
    val monitorType: MonitorType,
)

/**
 * Gets the audio monitor type of an input.
 *
 * @param inputName Name of the input to get the audio monitor type of
 * @param inputUuid UUID of the input to get the audio monitor type of
 * @return Audio monitor type
 */
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

package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GetInputVolumeRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
)

@Serializable
data class GetInputVolumeResponse(
    @SerialName("inputVolumeMul") val mul: Double,
    @SerialName("inputVolumeDb") val db: Double,
)

// Gets the volume of an input
suspend fun ObsSession.getInputVolume(
    name: String? = null,
    uuid: String? = null,
): GetInputVolumeResponse =
    callMethod("GetInputVolume", GetInputVolumeRequest(name, uuid))

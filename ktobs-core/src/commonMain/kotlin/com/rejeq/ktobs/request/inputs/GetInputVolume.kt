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

/**
 * @property mul Volume setting in mul
 * @property db Volume setting in dB
 */
@Serializable
data class GetInputVolumeResponse(
    @SerialName("inputVolumeMul") val mul: Double,
    @SerialName("inputVolumeDb") val db: Double,
)

/**
 * Gets the current volume setting of an input.
 *
 * @param name Name of the input to get the volume of
 * @param uuid UUID of the input to get the volume of
 */
suspend fun ObsSession.getInputVolume(
    name: String? = null,
    uuid: String? = null,
): GetInputVolumeResponse =
    callMethod("GetInputVolume", GetInputVolumeRequest(name, uuid))

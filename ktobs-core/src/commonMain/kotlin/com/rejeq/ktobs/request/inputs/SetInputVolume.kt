package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetInputVolumeRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
    val inputVolumeMul: Double? = null,
    val inputVolumeDb: Double? = null,
)

/**
 * Sets the volume setting of an input.
 *
 * @param name Name of the input to set the volume of
 * @param uuid UUID of the input to set the volume of
 * @param mul Volume setting in mul (amplification) (optional)
 * @param db Volume setting in dB (optional)
 */
suspend fun ObsSession.setInputVolume(
    name: String? = null,
    uuid: String? = null,
    mul: Double? = null,
    db: Double? = null,
) = callUnitMethod(
    "SetInputVolume",
    SetInputVolumeRequest(name, uuid, mul, db),
)

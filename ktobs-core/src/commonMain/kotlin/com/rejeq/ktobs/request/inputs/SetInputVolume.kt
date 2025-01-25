package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class SetInputVolumeRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
    val inputVolumeMul: Double? = null,
    val inputVolumeDb: Double? = null,
)

// Sets the volume of an input
suspend fun ObsSession.setInputVolume(
    name: String? = null,
    uuid: String? = null,
    mul: Double? = null,
    db: Double? = null,
) = callUnitMethod(
    "SetInputVolume",
    SetInputVolumeRequest(name, uuid, mul, db),
)

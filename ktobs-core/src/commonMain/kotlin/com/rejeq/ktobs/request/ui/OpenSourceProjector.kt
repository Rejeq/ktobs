package com.rejeq.ktobs.request.ui

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class OpenSourceProjectorRequest(
    val sourceName: String? = null,
    val sourceUuid: String? = null,
    val monitorIndex: Int? = null,
    val projectorGeometry: String? = null,
)

// Opens a projector for a source
// Note: Geometry string is in the format: "left,top,width,height"
suspend fun ObsSession.openSourceProjector(
    name: String? = null,
    uuid: String? = null,
    monitorIndex: Int? = null,
    geometry: String? = null,
) = callUnitMethod(
    "OpenSourceProjector",
    OpenSourceProjectorRequest(name, uuid, monitorIndex, geometry),
)

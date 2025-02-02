package com.rejeq.ktobs.request.ui

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import com.rejeq.ktobs.model.VideoMixType
import kotlinx.serialization.Serializable

@Serializable
class OpenVideoMixProjectorRequest(
    val videoMixType: VideoMixType,
    val monitorIndex: Int? = null,
    val projectorGeometry: String? = null,
)

// Opens a projector for a specific output video mix
// Note: Geometry string is in the format: "left,top,width,height"
suspend fun ObsSession.openVideoMixProjector(
    type: VideoMixType,
    monitorIndex: Int? = null,
    geometry: String? = null,
) = callUnitMethod(
    "OpenVideoMixProjector",
    OpenVideoMixProjectorRequest(type, monitorIndex, geometry),
)

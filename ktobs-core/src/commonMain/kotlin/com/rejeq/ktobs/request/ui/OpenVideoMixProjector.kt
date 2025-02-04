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

/**
 * Opens a projector for a specific output video mix.
 *
 * @param videoMixType Type of mix to open
 * @param monitorIndex Monitor index,
 *        use GetMonitorList to obtain index
 *        use -1 for windowed mode
 * @param projectorGeometry Size/Position data for a windowed projector as QRect
 *        string. Mutually exclusive with monitorIndex
 */
suspend fun ObsSession.openVideoMixProjector(
    type: VideoMixType,
    monitorIndex: Int? = null,
    geometry: String? = null,
) = callUnitMethod(
    "OpenVideoMixProjector",
    OpenVideoMixProjectorRequest(type, monitorIndex, geometry),
)

package com.rejeq.ktobs.request.ui

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class VideoMixType {
    @SerialName("OBS_WEBSOCKET_VIDEO_MIX_TYPE_PREVIEW")
    Preview,

    @SerialName("OBS_WEBSOCKET_VIDEO_MIX_TYPE_PROGRAM")
    Program,

    @SerialName("OBS_WEBSOCKET_VIDEO_MIX_TYPE_MULTIVIEW")
    Multiview,
}

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

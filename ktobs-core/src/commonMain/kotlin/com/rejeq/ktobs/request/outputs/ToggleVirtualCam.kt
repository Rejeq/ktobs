package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ToggleVirtualCamResponse(
    @SerialName("outputActive") val active: Boolean,
)

// Toggles the state of the virtualcam output
suspend fun ObsSession.toggleVirtualCam(): Boolean =
    callMethod<ToggleVirtualCamResponse>("ToggleVirtualCam").active

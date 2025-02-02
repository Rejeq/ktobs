package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GetVirtualCamStatusResponse(
    @SerialName("outputActive") val active: Boolean,
)

// Gets the status of the virtualcam output
suspend fun ObsSession.getVirtualCamStatus(): Boolean =
    callMethod<GetVirtualCamStatusResponse>("GetVirtualCamStatus").active

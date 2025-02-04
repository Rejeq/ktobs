package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod

/**
 * Stops the virtualcam output.
 */
suspend fun ObsSession.stopVirtualCam() = callUnitMethod("StopVirtualCam") 

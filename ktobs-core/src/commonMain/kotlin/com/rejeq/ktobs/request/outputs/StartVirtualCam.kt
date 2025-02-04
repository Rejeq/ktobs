package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod

/**
 * Starts the virtualcam output.
 */
suspend fun ObsSession.startVirtualCam() = callUnitMethod("StartVirtualCam") 

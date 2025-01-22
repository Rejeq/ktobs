package com.rejeq.ktobs.request.transitions

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class SetTBarPositionRequest(
    val position: Double,
    val release: Boolean? = null,
)

// Sets the position of the TBar
// Position must be between 0.0 and 1.0
suspend fun ObsSession.setTBarPosition(
    position: Double,
    release: Boolean? = null,
) = callUnitMethod(
    "SetTBarPosition",
    SetTBarPositionRequest(position, release),
)

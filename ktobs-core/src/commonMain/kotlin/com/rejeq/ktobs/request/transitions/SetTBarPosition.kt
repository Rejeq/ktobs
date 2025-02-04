package com.rejeq.ktobs.request.transitions

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetTBarPositionRequest(
    val position: Double,
    val release: Boolean? = null,
)

/**
 * Sets the position of the TBar.
 *
 * Very important note: This is an asynchronous request that will be processed
 * at a later time.
 * You cannot expect the position to be accurate when the response comes back.
 *
 * @param position New position, between 0.0 and 1.0
 * @param release Whether to release the TBar. Only set false if you know that
 *        you will be sending more positions
 */
suspend fun ObsSession.setTBarPosition(
    position: Double,
    release: Boolean? = null,
) = callUnitMethod(
    "SetTBarPosition",
    SetTBarPositionRequest(position, release),
)

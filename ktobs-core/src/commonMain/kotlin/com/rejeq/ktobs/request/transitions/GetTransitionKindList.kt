package com.rejeq.ktobs.request.transitions

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
class GetTransitionKindListResponse(
    val transitionKinds: List<String>,
)

/**
 * Gets an array of all available transition kinds.
 *
 * Similar to GetInputKindList.
 *
 * @return Array of transition kinds
 */
suspend fun ObsSession.getTransitionKindList(): List<String> =
    callMethod<GetTransitionKindListResponse>(
        "GetTransitionKindList",
    ).transitionKinds

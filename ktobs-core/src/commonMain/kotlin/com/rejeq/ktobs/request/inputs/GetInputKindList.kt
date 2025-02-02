package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
class GetInputKindListRequest(
    val unversioned: Boolean?,
)

@Serializable
class GetInputKindListResponse(
    val inputKinds: List<String>,
)

// Gets an array of all available input kinds in OBS
suspend fun ObsSession.getInputKindList(
    unversioned: Boolean? = false,
): List<String> =
    callMethod<GetInputKindListResponse, GetInputKindListRequest>(
        "GetInputKindList",
        GetInputKindListRequest(unversioned),
    ).inputKinds

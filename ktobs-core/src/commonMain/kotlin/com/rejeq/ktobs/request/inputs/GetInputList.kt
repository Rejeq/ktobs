package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import com.rejeq.ktobs.model.Input
import kotlinx.serialization.Serializable

@Serializable
class GetInputListRequest(
    val inputKind: String?,
)

@Serializable
class GetInputListResponse(
    val inputs: List<Input>,
)

// Gets an array of all inputs in OBS
suspend fun ObsSession.getInputList(inputKind: String? = null): List<Input> =
    callMethod<GetInputListResponse, GetInputListRequest>(
        "GetInputList",
        GetInputListRequest(inputKind),
    ).inputs

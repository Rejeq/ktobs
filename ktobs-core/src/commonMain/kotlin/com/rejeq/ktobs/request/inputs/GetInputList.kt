package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GetInputListRequest(
    val inputKind: String?,
)

@Serializable
data class Input(
    @SerialName("inputName") val name: String,
    @SerialName("inputUuid") val uuid: String,
    @SerialName("inputKind") val kind: String,
    @SerialName("unversionedInputKind") val unversionedKind: String,
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

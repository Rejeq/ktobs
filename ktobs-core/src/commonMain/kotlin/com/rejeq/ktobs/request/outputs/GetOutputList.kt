package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Output(
    @SerialName("outputName") val name: String,
    @SerialName("outputKind") val kind: String,
    @SerialName("outputWidth") val width: Int,
    @SerialName("outputHeight") val height: Int,
    @SerialName("outputActive") val active: Boolean,
    @SerialName("outputFlags") val flags: Map<String, Boolean>,
)

@Serializable
data class GetOutputListResponse(
    val outputs: List<Output>,
)

// Gets the list of available outputs
suspend fun ObsSession.getOutputList(): List<Output> =
    callMethod<GetOutputListResponse>("GetOutputList").outputs

package com.rejeq.ktobs.request.outputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import com.rejeq.ktobs.model.Output
import kotlinx.serialization.Serializable

@Serializable
class GetOutputListResponse(
    val outputs: List<Output>,
)

// Gets the list of available outputs
suspend fun ObsSession.getOutputList(): List<Output> =
    callMethod<GetOutputListResponse>("GetOutputList").outputs

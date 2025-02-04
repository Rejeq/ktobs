package com.rejeq.ktobs.request.filters

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GetSourceFilterKindListResponse(
    @SerialName("sourceFilterKinds") val kinds: List<String>,
)

/**
 * Gets an array of all available source filter kinds
 *
 * @return Array of source filter kinds
 **/
suspend fun ObsSession.getSourceFilterKindList(): List<String> =
    callMethod<GetSourceFilterKindListResponse>("GetSourceFilterKindList").kinds

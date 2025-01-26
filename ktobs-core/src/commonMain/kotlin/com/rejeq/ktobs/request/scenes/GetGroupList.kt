package com.rejeq.ktobs.request.scenes

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
data class GetGroupListResponse(
    val groups: List<String>,
)

// Gets an array of all groups in OBS
// Groups in OBS are actually scenes, but renamed and modified.
// In obs-websocket, we treat them as scenes where we can.
suspend fun ObsSession.getGroupList(): List<String> =
    callMethod<GetGroupListResponse>("GetGroupList").groups

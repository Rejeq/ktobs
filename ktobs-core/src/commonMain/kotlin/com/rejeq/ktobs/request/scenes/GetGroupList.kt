package com.rejeq.ktobs.request.scenes

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
class GetGroupListResponse(
    val groups: List<String>,
)

/**
 * Gets an array of all groups in OBS.
 *
 * Groups in OBS are actually scenes, but renamed and modified.
 * In obs-websocket, groups are treated specially and have their own set of
 * methods.
 *
 * @return Array of groups
 */
suspend fun ObsSession.getGroupList(): List<String> =
    callMethod<GetGroupListResponse>("GetGroupList").groups

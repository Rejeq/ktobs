package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
class GetSceneItemLockedRequest(
    val sceneName: String?,
    val sceneUuid: String?,
    val sceneItemId: Int,
)

@Serializable
class GetSceneItemLockedResponse(
    val sceneItemLocked: Boolean,
)

/**
 * Gets the lock state of a scene item.
 *
 * @param sceneName Name of the scene the item is in
 * @param sceneUuid UUID of the scene the item is in
 * @param sceneItemId Numeric ID of the scene item
 * @return Whether the scene item is locked
 */
suspend fun ObsSession.getSceneItemLocked(
    sceneName: String? = null,
    sceneUuid: String? = null,
    sceneItemId: Int,
): Boolean =
    callMethod<GetSceneItemLockedResponse, GetSceneItemLockedRequest>(
        "GetSceneItemLocked",
        GetSceneItemLockedRequest(sceneName, sceneUuid, sceneItemId),
    ).sceneItemLocked

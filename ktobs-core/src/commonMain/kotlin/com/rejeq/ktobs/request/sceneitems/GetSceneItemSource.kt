package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GetSceneItemSourceRequest(
    val sceneName: String? = null,
    val sceneUuid: String? = null,
    val sceneItemId: Int,
)

/**
 * @property name Name of the source associated with the scene item
 * @property uuid UUID of the source associated with the scene item
 */
@Serializable
data class GetSceneItemSourceResponse(
    @SerialName("sourceName") val name: String,
    @SerialName("sourceUuid") val uuid: String,
)

/**
 * Gets the source associated with a scene item.
 *
 * @param sceneName Name of the scene or group the item is in
 * @param sceneUuid UUID of the scene or group the item is in
 * @param sceneItemId Numeric ID of the scene item
 */
suspend fun ObsSession.getSceneItemSource(
    sceneName: String? = null,
    sceneUuid: String? = null,
    sceneItemId: Int,
): GetSceneItemSourceResponse =
    callMethod(
        "GetSceneItemSource",
        GetSceneItemSourceRequest(sceneName, sceneUuid, sceneItemId),
    )

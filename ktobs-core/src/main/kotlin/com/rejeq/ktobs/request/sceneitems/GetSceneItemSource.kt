package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSceneItemSourceRequest(
    val sceneName: String? = null,
    val sceneUuid: String? = null,
    val sceneItemId: Int,
)

@Serializable
data class GetSceneItemSourceResponse(
    @SerialName("sourceName") val name: String,
    @SerialName("sourceUuid") val uuid: String,
)

// Gets the source associated with a scene item
suspend fun ObsSession.getSceneItemSource(
    sceneName: String? = null,
    sceneUuid: String? = null,
    sceneItemId: Int,
): GetSceneItemSourceResponse =
    callMethod(
        "GetSceneItemSource",
        GetSceneItemSourceRequest(sceneName, sceneUuid, sceneItemId),
    )

package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSceneCollectionListResponse(
    /** The name of the current scene collection */
    @SerialName("currentSceneCollectionName") val currentName: String,
    /** Array of all available scene collections */
    @SerialName("sceneCollections") val collections: List<String>,
)

/**
 * Gets an array of all scene collections
 */
suspend fun ObsSession.getSceneCollectionList():
    GetSceneCollectionListResponse =
    callMethod("GetSceneCollectionList")

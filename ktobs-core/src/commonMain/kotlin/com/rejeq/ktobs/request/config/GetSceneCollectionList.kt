package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property currentName The name of the current scene collection
 * @property collections Array of all available scene collections
 */
@Serializable
data class GetSceneCollectionListResponse(
    @SerialName("currentSceneCollectionName") val currentName: String,
    @SerialName("sceneCollections") val collections: List<String>,
)

/**
 * Gets an array of all scene collections
 */
suspend fun ObsSession.getSceneCollectionList():
    GetSceneCollectionListResponse =
    callMethod("GetSceneCollectionList")

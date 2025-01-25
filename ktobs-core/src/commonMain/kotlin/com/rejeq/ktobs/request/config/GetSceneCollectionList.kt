package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetSceneCollectionListResponse(
    @SerialName("currentSceneCollectionName") val currentName: String,
    @SerialName("sceneCollections") val collections: List<String>,
)

// Gets an array of all scene collections
suspend fun ObsSession.getSceneCollectionList():
    GetSceneCollectionListResponse =
    callMethod("GetSceneCollectionList")

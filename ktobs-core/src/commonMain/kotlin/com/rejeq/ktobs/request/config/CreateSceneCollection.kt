package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class CreateSceneCollectionRequest(
    val sceneCollectionName: String,
)

// Creates a new scene collection, switching to it in the process
suspend fun ObsSession.createSceneCollection(name: String) =
    callUnitMethod("CreateSceneCollection", CreateSceneCollectionRequest(name))

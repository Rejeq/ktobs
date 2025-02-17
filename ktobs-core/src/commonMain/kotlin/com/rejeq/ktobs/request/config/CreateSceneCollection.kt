package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class CreateSceneCollectionRequest(
    val sceneCollectionName: String,
)

/**
 * Creates a new scene collection, switching to it in the process.
 *
 * Note: This will block until the collection has finished changing.
 *
 * @param name Name for the new scene collection
 */
suspend fun ObsSession.createSceneCollection(name: String) =
    callUnitMethod("CreateSceneCollection", CreateSceneCollectionRequest(name))

package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetCurrentSceneCollectionRequest(
    val sceneCollectionName: String,
)

// Switches to a scene collection. Note: This will block until the collection
// has finished changing
suspend fun ObsSession.setCurrentSceneCollection(name: String) =
    callUnitMethod(
        "SetCurrentSceneCollection",
        SetCurrentSceneCollectionRequest(name),
    )

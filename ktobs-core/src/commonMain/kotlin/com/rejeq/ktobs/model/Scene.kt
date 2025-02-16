package com.rejeq.ktobs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property name The name of the scene
 * @property uuid The uuid of the scene
 *           This is available starting from OBS WebSocket version 5.4.0.
 *           It will be `null` for earlier versions.
 * @property index The index of the scene
 **/
@Serializable
data class Scene(
    @SerialName("sceneName") val name: String,
    @SerialName("sceneUuid") val uuid: String? = null,
    @SerialName("sceneIndex") val index: Int,
)

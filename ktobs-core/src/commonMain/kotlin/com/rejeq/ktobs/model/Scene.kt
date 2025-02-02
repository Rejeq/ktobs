package com.rejeq.ktobs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Scene(
    @SerialName("sceneName") val name: String,
    @SerialName("sceneUuid") val uuid: String,
    @SerialName("sceneIndex") val index: Int,
)

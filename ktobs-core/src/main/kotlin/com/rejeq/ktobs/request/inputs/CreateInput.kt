package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class CreateInputRequest(
    val sceneName: String? = null,
    val sceneUuid: String? = null,
    val inputName: String,
    val inputKind: String,
    val inputSettings: JsonElement? = null,
    val sceneItemEnabled: Boolean? = true,
)

@Serializable
data class CreateInputResponse(
    val inputUuid: String,
    val sceneItemId: Long,
)

// Creates a new input, adding it as a scene item to the specified scene
suspend fun ObsSession.createInput(
    sceneName: String? = null,
    sceneUuid: String? = null,
    name: String,
    kind: String,
    settings: JsonElement? = null,
    enabled: Boolean? = true,
): CreateInputResponse =
    callMethod(
        "CreateInput",
        CreateInputRequest(
            sceneName,
            sceneUuid,
            name,
            kind,
            settings,
            enabled,
        ),
    )

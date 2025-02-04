package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
class CreateInputRequest(
    val sceneName: String? = null,
    val sceneUuid: String? = null,
    val inputName: String,
    val inputKind: String,
    val inputSettings: JsonElement? = null,
    val sceneItemEnabled: Boolean? = true,
)

/**
 * @property inputUuid UUID of the newly created input
 * @property sceneItemId ID of the newly created scene item
 */
@Serializable
data class CreateInputResponse(
    val inputUuid: String,
    val sceneItemId: Long,
)

/**
 * Creates a new input, adding it as a scene item to the specified scene.
 *
 * @param sceneName Name of the scene to add the input to
 * @param inputName Name of the new input to created
 * @param inputKind The kind of input to be created
 * @param inputSettings Settings object to initialize the input with,
 *        null if you want to use it as default settings
 * @param sceneItemEnabled Whether to set the created scene item to enabled or
 *        disabled
 */
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

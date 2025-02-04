package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
class GetInputDefaultSettingsRequest(
    val inputKind: String,
)

@Serializable
class GetInputDefaultSettingsResponse(
    val defaultInputSettings: JsonElement,
)

/**
 * Gets the default settings for an input kind.
 *
 * @param inputKind Input kind to get the default settings for
 * @return Object of default settings for the input kind
 */
suspend fun ObsSession.getInputDefaultSettings(kind: String): JsonElement =
    callMethod<GetInputDefaultSettingsResponse, GetInputDefaultSettingsRequest>(
        "GetInputDefaultSettings",
        GetInputDefaultSettingsRequest(kind),
    ).defaultInputSettings

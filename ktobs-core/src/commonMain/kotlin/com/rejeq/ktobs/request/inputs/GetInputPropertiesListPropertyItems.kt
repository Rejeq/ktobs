package com.rejeq.ktobs.request.inputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
class GetInputPropertiesListPropertyItemsRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
    val propertyName: String,
)

@Serializable
class GetInputPropertiesListPropertyItemsResponse(
    val propertyItems: List<JsonElement>,
)

// Gets the items of a list property from an input's properties
suspend fun ObsSession.getInputPropertiesListPropertyItems(
    inputName: String? = null,
    inputUuid: String? = null,
    propertyName: String,
): List<JsonElement> =
    callMethod<
        GetInputPropertiesListPropertyItemsResponse,
        GetInputPropertiesListPropertyItemsRequest,
    >(
        "GetInputPropertiesListPropertyItems",
        GetInputPropertiesListPropertyItemsRequest(
            inputName,
            inputUuid,
            propertyName,
        ),
    ).propertyItems

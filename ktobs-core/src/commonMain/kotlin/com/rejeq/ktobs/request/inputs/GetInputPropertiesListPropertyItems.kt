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

/**
 * Gets the items of a list property from an input's properties.
 *
 * Note: Use this in cases where an input provides a dynamic, selectable list of
 * items. For example, display capture, where it provides a list of available
 * displays.
 *
 * @param inputName Name of the input
 * @param inputUuid UUID of the input
 * @param propertyName Name of the list property to get the items of
 */
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

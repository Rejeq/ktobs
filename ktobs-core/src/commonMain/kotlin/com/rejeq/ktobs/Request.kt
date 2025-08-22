package com.rejeq.ktobs

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.serializer
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
class RequestOpCode(
    val requestType: String,
    val requestId: String,
    val requestData: JsonElement,
) : OpCode

@Serializable
class RequestResponseOpCode(
    val requestType: String,
    val requestId: String,
    val requestStatus: RequestStatus,
    val responseData: JsonElement? = null,
) : OpCode

@Serializable
class RequestStatus(
    val result: Boolean,
    val code: RequestCode,
    val comment: String? = null,
)

/**
 * Exception thrown when an OBS WebSocket request fails.
 *
 * @property type The type of request that failed
 * @property code The error code returned by the server
 * @param msg Optional error message providing more details
 */
class ObsRequestException(
    val type: String,
    val code: RequestCode,
    msg: String? = null,
) : Exception("Request '$type' failed with '$code' code: $msg")

/**
 * Calls an OBS WebSocket method that returns data but takes no parameters.
 *
 * @param type The type of request to make
 * @return The deserialized response data
 * @throws ObsRequestException if the request fails
 */
suspend inline fun <reified Output> ObsSession.callMethod(
    type: String,
): Output = callMethod<Output, Unit>(type, null)

/**
 * Calls an OBS WebSocket method with parameters and returns data.
 *
 * @param type The type of request to make
 * @param data The request parameters to send
 * @return The deserialized response data
 * @throws ObsRequestException if the request fails
 */
@OptIn(ExperimentalUuidApi::class)
suspend inline fun <reified Output, reified Input> ObsSession.callMethod(
    type: String,
    data: Input?,
): Output =
    callMethod(
        type,
        data,
        inputSer = serializer<Input?>(),
        outputSer = serializer<Output>(),
        outputIsNullable = null is Output,
    )

/**
 * Calls an OBS WebSocket method with parameters and returns data.
 *
 * @param type The type of request to make
 * @param data The request parameters to send
 * @param inputSer The serializer of input type
 * @param outputSer The serializer of the output type
 * @param outputIsNullable Whether output type allow nullable values
 * @return The deserialized response data
 * @throws ObsRequestException if the request fails
 */
@OptIn(ExperimentalUuidApi::class)
suspend fun <Output, Input> ObsSession.callMethod(
    type: String,
    data: Input?,
    inputSer: KSerializer<Input?>,
    outputSer: KSerializer<Output>,
    outputIsNullable: Boolean,
): Output =
    acquireUuid { uuid, response ->
        sendRequest(type, uuid, data, inputSer)
        handleRequest(response.await(), outputSer, outputIsNullable)
    }

/**
 * Calls an OBS WebSocket method that returns no data and takes no parameters.
 *
 * @param type The type of request to make
 * @throws ObsRequestException if the request fails
 */
suspend inline fun ObsSession.callUnitMethod(type: String) =
    callUnitMethod<Unit>(type, null)

/**
 * Calls an OBS WebSocket method with parameters that returns no data.
 *
 * @param type The type of request to make
 * @param data The request parameters to send
 * @throws ObsRequestException if the request fails
 */
@OptIn(ExperimentalUuidApi::class)
suspend inline fun <reified Input> ObsSession.callUnitMethod(
    type: String,
    data: Input?,
) = callUnitMethod(type, data, inputSer = serializer<Input?>())

/**
 * Calls an OBS WebSocket method with parameters that returns no data.
 *
 * @param type The type of request to make
 * @param data The request parameters to send
 * @param inputSer The serializer of input type
 * @throws ObsRequestException if the request fails
 */
@OptIn(ExperimentalUuidApi::class)
suspend fun <Input> ObsSession.callUnitMethod(
    type: String,
    data: Input?,
    inputSer: KSerializer<Input?>,
) = acquireUuid { uuid, response ->
    sendRequest(type, uuid, data, inputSer)
    handleUnitRequest(response.await())
}

/**
 * Sends a request to the OBS WebSocket server.
 *
 * @param requestType The type of request to make
 * @param uuid Unique identifier for the request
 * @param data The request parameters to send
 * @param inputSer The serializer of input type
 */
@OptIn(ExperimentalUuidApi::class)
private suspend fun <Input> ObsSession.sendRequest(
    requestType: String,
    uuid: Uuid,
    data: Input?,
    inputSer: KSerializer<Input?>,
) = ws.sendMessage(
    RequestOpCode(
        requestType,
        uuid.toString(),
        ws.json.encodeToJsonElement(inputSer, data),
    ),
)

/**
 * Handles a response from a request that returns no data.
 *
 * @param response The response received from the server
 * @throws ObsRequestException if the request was not successful
 */
private fun handleUnitRequest(response: RequestResponseOpCode) {
    val status = response.requestStatus
    if (!status.result) {
        throw ObsRequestException(
            type = response.requestType,
            code = status.code,
            msg = status.comment,
        )
    }
}

/**
 * Handles a response from a request that returns data.
 *
 * @param response The response received from the server
 * @return The deserialized response data
 * @param outputSer The serializer of the output type
 * @param outputIsNullable Whether output type allow nullable values
 * @throws ObsRequestException if the request failed or response data is missing
 */
private fun <Output> ObsSession.handleRequest(
    response: RequestResponseOpCode,
    outputSer: KSerializer<Output>,
    outputIsNullable: Boolean,
): Output {
    val status = response.requestStatus
    if (!status.result) {
        throw ObsRequestException(
            type = response.requestType,
            code = status.code,
            msg = status.comment,
        )
    }

    if (response.responseData == null) {
        if (outputIsNullable) {
            @Suppress("UNCHECKED_CAST")
            return null as Output
        } else {
            throw ObsRequestException(
                type = response.requestType,
                code = RequestCode.Unknown,
                msg = "Response data is not returned",
            )
        }
    }

    return ws.json.decodeFromJsonElement(
        outputSer,
        response.responseData,
    )
}

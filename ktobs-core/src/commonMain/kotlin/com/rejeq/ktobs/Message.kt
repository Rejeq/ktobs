package com.rejeq.ktobs

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.serializer

/**
 * Base interface for all OBS WebSocket operation codes (OpCodes).
 * All message types that can be sent or received must implement this interface.
 */
@Serializable
sealed interface OpCode

/**
 * Represents a raw message exchanged with the OBS WebSocket server.
 *
 * @property op Operation code identifying the message type
 * @property d The message data as a JSON element
 */
@Serializable
class ObsMessage(
    val op: Int,
    val d: JsonElement,
)

/**
 * Receives and deserializes a message from the WebSocket connection.
 *
 * @return The deserialized message of type [T]
 * @throws UnexpectedOpCode if the received message is not of the expected type
 */
suspend inline fun <reified T : OpCode> WsSession.receiveMessage(): T {
    val data = receiveMessageBase()

    return data as? T ?: throw UnexpectedOpCode(
        "Deserialized data is not of expected type. " +
            "Expected: ${T::class.simpleName}, Got: ${data::class.simpleName}",
    )
}

/**
 * Receives and deserializes a base message from the WebSocket connection.
 *
 * @return The deserialized message as an [OpCode]
 * @throws UnknownOpCode if the operation code is not recognized
 */
suspend fun WsSession.receiveMessageBase(): OpCode {
    val msg = receiveMessage()
    val serializer = getSerializer(msg.op)

    return Json.decodeFromJsonElement(serializer, msg.d)
}

/**
 * Serializes and sends a message over the WebSocket connection.
 *
 * @param data The message to send
 */
@OptIn(InternalSerializationApi::class)
suspend inline fun <reified T : OpCode> WsSession.sendMessage(data: T) =
    sendMessage(
        ObsMessage(
            getOpCode(data),
            Json.encodeToJsonElement(T::class.serializer(), data),
        ),
    )

/**
 * Gets the appropriate serializer for a given operation code.
 *
 * @param op The operation code to get the serializer for
 * @return The serializer for the specified operation code
 * @throws UnknownOpCode if the operation code is not recognized
 */
fun getSerializer(op: Int): KSerializer<out OpCode> =
    when (op) {
        0 -> HelloOpCode.serializer()
        1 -> IdentifyOpCode.serializer()
        2 -> IdentifiedOpCode.serializer()
        3 -> ReidentifyOpCode.serializer()
        5 -> EventOpCode.serializer()
        6 -> RequestOpCode.serializer()
        7 -> RequestResponseOpCode.serializer()
        else -> {
            throw UnknownOpCode("'$op' OpCode is not expected")
        }
    }

/**
 * Gets the numeric operation code for a given [OpCode] instance.
 *
 * @param data The [OpCode] instance to get the operation code for
 * @return The numeric operation code
 */
fun getOpCode(data: OpCode): Int =
    when (data) {
        is HelloOpCode -> 0
        is IdentifyOpCode -> 1
        is IdentifiedOpCode -> 2
        is ReidentifyOpCode -> 3
        is EventOpCode -> 5
        is RequestOpCode -> 6
        is RequestResponseOpCode -> 7
    }

/** Exception thrown when an unrecognized operation code is encountered. */
class UnknownOpCode(
    msg: String,
) : Exception(msg)

/** Exception thrown when a message is not of the expected type. */
class UnexpectedOpCode(
    msg: String,
) : Exception(msg)

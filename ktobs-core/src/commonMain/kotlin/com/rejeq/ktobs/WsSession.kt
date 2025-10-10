package com.rejeq.ktobs

import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

/**
 * Representing a WebSocket session.
 * Provides basic operations for sending and receiving messages
 */
interface WsSession {
    val json: Json

    /**
     * Receives a message from the WebSocket connection.
     *
     * @return The received obs message
     * @throws ClosedReceiveChannelException if websocket channel was closed.
     *         Used by library in the auth stage
     * @throws SerializationException if failed to deserialize the incoming data
     * @throws Exception if error occur during receive
     */
    suspend fun receiveMessage(): ObsMessage

    /**
     * Sends a message through the WebSocket connection.
     *
     * @param msg The obs message to send
     * @throws SerializationException if failed to serialize the message
     * @throws Exception if error occur during send
     */
    suspend fun sendMessage(msg: ObsMessage)

    /**
     * Gets the reason for connection closure if the WebSocket connection was
     * closed.
     *
     * @return The close reason if connection was closed, null if connection is
     *         still open
     */
    suspend fun getCloseReason(): ObsCloseReason?
}

package com.rejeq.ktobs.request.general

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
class BroadcastCustomEventRequest(
    val eventData: JsonElement,
)

// Broadcasts a custom event to all WebSocket clients
suspend fun ObsSession.broadcastCustomEvent(data: JsonElement) {
    callUnitMethod("BroadcastCustomEvent", BroadcastCustomEventRequest(data))
}

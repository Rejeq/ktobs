package com.rejeq.ktobs

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
data class EventOpCode(
    val eventType: String,
    val eventIntent: Int,
    val eventData: JsonObject? = null,
) : OpCode {
    inline fun <reified Data : Any> get(): Data {
        checkNotNull(eventData) { "eventData should not be null" }

        return Json.decodeFromJsonElement(eventData)
    }
}

package com.rejeq.ktobs

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class EventOpCode(
    val eventType: String,
    val eventIntent: Int,
    val eventData: JsonObject? = null,
) : OpCode

typealias ObsEventHandler = suspend ObsSession.(event: EventOpCode) -> Unit

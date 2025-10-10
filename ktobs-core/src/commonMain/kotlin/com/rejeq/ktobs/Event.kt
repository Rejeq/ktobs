package com.rejeq.ktobs

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class EventOpCode(
    val eventType: String,
    val eventIntent: Int,
    val eventData: JsonObject? = null,
) : OpCode

typealias ObsEventHandler = suspend ObsSession.(event: EventOpCode) -> Unit

/**
 * Awaits the first event in this [Flow] matching [type].
 */
suspend fun Flow<EventOpCode>.awaitEvent(type: String): EventOpCode =
    this.first { it.eventType == type }

/**
 * Awaits the first event in this [Flow] matching [type].
 * Starts listening before invoking [action] to avoid missing early events.
 */
suspend fun Flow<EventOpCode>.awaitEvent(
    type: String,
    action: suspend () -> Unit,
): EventOpCode =
    coroutineScope {
        val deferredEvent =
            async(start = CoroutineStart.UNDISPATCHED) {
                this@awaitEvent.first { it.eventType == type }
            }

        action.invoke()
        deferredEvent.await()
    }

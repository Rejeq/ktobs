package com.rejeq.ktobs.sample

import com.rejeq.ktobs.EventOpCode
import com.rejeq.ktobs.ObsAuthException
import com.rejeq.ktobs.ObsEventSubs
import com.rejeq.ktobs.ObsRequestException
import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.awaitEvent
import com.rejeq.ktobs.event.general.ExitStartedEvent
import com.rejeq.ktobs.event.inputs.InputCreatedEvent
import com.rejeq.ktobs.event.inputs.InputCreatedEventData
import com.rejeq.ktobs.event.inputs.InputRemovedEvent
import com.rejeq.ktobs.event.inputs.InputRemovedEventData
import com.rejeq.ktobs.ktor.ObsSessionBuilder
import com.rejeq.ktobs.request.inputs.createInput
import com.rejeq.ktobs.request.inputs.getInputDefaultSettings
import com.rejeq.ktobs.request.inputs.getInputKindList
import com.rejeq.ktobs.request.inputs.getInputList
import com.rejeq.ktobs.request.sceneitems.getSceneItemId
import com.rejeq.ktobs.request.sceneitems.getSceneItemList
import com.rejeq.ktobs.request.sceneitems.removeSceneItem
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.net.ConnectException

val prettyJson =
    Json {
        prettyPrint = true
    }

inline fun <reified T> T.toPrettyString(): String =
    prettyJson.encodeToString(this)

fun main(): Unit =
    runBlocking {
        val client =
            HttpClient(CIO) {
                install(WebSockets) {
                    pingIntervalMillis = 20_000
                    contentConverter =
                        KotlinxWebsocketSerializationConverter(Json)
                }
            }

        val session =
            ObsSessionBuilder(client).apply {
                host = "127.0.0.1"
                port = 4455
                password = "12345678"
                eventSubs = ObsEventSubs.All
            }

        val eventFlow = MutableSharedFlow<EventOpCode>()
        session.onEvent = { ev ->
            eventFlow.emit(ev)

            when (ev.eventType) {
                ExitStartedEvent -> {
                    println("Obs exit started")
                }
                InputCreatedEvent -> {
                    val data = ev.get<InputCreatedEventData>()
                    println("Input was created: ${data.inputName}")
                }
                InputRemovedEvent -> {
                    val data = ev.get<InputRemovedEventData>()
                    println("Input was removed: ${data.inputName}")
                }
                else -> {
                    println("Unexpected event was received: $ev")
                }
            }
        }

        try {
            session.connect {
                obsMain(eventFlow.asSharedFlow())

                // Wait for any incoming events
                delay(500)
            }
        } catch (e: Exception) {
            val msg =
                when (e) {
                    is ObsAuthException -> "Auth failed: $e"
                    is UnresolvedAddressException -> "Unknown host: $e"
                    is ConnectTimeoutException -> "Connection timeout: $e"
                    is ConnectException -> "Connection refused: $e"
                    is ObsRequestException -> "Request failed: $e"
                    else -> throw e
                }

            println(msg)
        }

        client.close()
    }

suspend fun ObsSession.obsMain(eventFlow: Flow<EventOpCode>) {
    val sceneName = "Scene"
    val inputName = "FFmpeg Source"

    val kindList = getInputKindList()
    println("Kind list: ${kindList.toPrettyString()}\n")

    val itemList = getSceneItemList(sceneName)
    println("Item list: ${itemList.toPrettyString()}\n")

    val ffmpegInputList = getInputList("ffmpeg_source")
    println("FFmpeg input list: ${ffmpegInputList.toPrettyString()}\n")

    val isInputExist = ffmpegInputList.any { it.name == inputName }
    println("Is input exist: $isInputExist")

    if (isInputExist) {
        val itemId =
            getSceneItemId(
                sceneName = sceneName,
                sourceName = inputName,
            )

        removeSceneItem(
            sceneName = sceneName,
            itemId = itemId,
        )

        // It's safer to use awaitEvent with lambda argument.
        // Omitted for brevity purposes
        val event = eventFlow.awaitEvent(InputRemovedEvent)
        println("Event was captured by obsMain: $event")
    }

    val default = getInputDefaultSettings("ffmpeg_source") as JsonObject
    println("Default input settings: ${default.toPrettyString()}\n")

    createInput(
        sceneName = sceneName,
        name = inputName,
        kind = "ffmpeg_source",
        settings =
            buildJsonObject {
                put("input", "rtmp://some")
                put("is_local_file", false)
            },
    )
}

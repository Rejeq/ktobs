package com.rejeq.ktobs.sample

import com.rejeq.ktobs.ObsEventSub
import com.rejeq.ktobs.ObsRequestException
import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.event.ExitStartedEvent
import com.rejeq.ktobs.event.VendorEvent
import com.rejeq.ktobs.event.VendorEventData
import com.rejeq.ktobs.ktor.ObsSessionBuilder
import com.rejeq.ktobs.request.inputs.*
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

val prettyJson =
    Json {
        prettyPrint = true
    }

inline fun <reified T> T.toPrettyString(): String =
    prettyJson.encodeToString(this)

fun main() =
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
            ObsSessionBuilder(client).let {
                it.host = "127.0.0.1"
                it.port = 4455
                it.password = "12345678"
                it.eventSubs = ObsEventSub.All()

                it
            }

        session.onEvent = { ev ->
            println("Event happened: $ev")

            when (ev.eventType) {
                ExitStartedEvent -> {
                    println("Obs exit started")
                }
                VendorEvent -> {
                    val vendor = ev.get<VendorEventData>()
                    println("Vendor event data: $vendor")
                }
            }
        }

        session.connect {
            try {
                obsMain()
            } catch (e: ObsRequestException) {
                println(e)
            }
        }

        client.close()
    }

suspend fun ObsSession.obsMain() {
//    val itemList = getSceneItemList("Scene")
//    println("Item list: ${itemList.toPrettyString()}\n")
//
//    val imageInputList = getInputList("image_source")
//    println(
//        "Image Input list: ${imageInputList.toPrettyString()}\n",
//    )

    val kindList = getInputKindList()
    println("Kind list: ${kindList.toPrettyString()}\n")

    val ffmpegInputList = getInputList("ffmpeg_source")
    println("FFmpeg input list: ${ffmpegInputList.toPrettyString()}\n")

    val input = ffmpegInputList.firstOrNull()
    input?.let { input ->
        val inputSettings = getInputSettings(input.name)
        println(
            "Input '${input.name}' settings: " +
                "${inputSettings.toPrettyString()}\n",
        )

        val default: JsonObject =
            getInputDefaultSettings(
                inputSettings.kind,
            ) as JsonObject

        println(
            "Default settings: ${default.toPrettyString()}\n",
        )
    }

    val response =
        createInput(
            sceneName = "Scene",
            name = "SomeAnotherTest",
            kind = "ffmpeg_source",
            settings =
                JsonObject(
                    mapOf(
                        "input" to JsonPrimitive("rtmp://some"),
                        "is_local_file" to JsonPrimitive(false),
                    ),
                ),
        )
    println("response: ${response.toPrettyString()}")

//    val sceneList = getSceneList()
//    println("Scene list: $sceneList")
//
//    val groupList = getGroupList()
//    println("Group list: $groupList")
//
//    val currScene = getCurrentProgramScene()
//    println("current scene: $currScene")
//
//    var scene = sceneList.scenes.find { it.name == "Something" }
//    if (scene == null) {
//        val uuid = createScene("Something")
//        scene = Scene("Something", uuid, -1)
//    }
//
//    removeScene(scene.name, scene.uuid)
//
//    println("Successfully handled")
}

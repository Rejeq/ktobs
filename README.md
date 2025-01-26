# Ktobs

Ktobs is a Kotlin library that provides a convenient wrapper around the
[OBS WebSocket API](https://github.com/obsproject/obs-websocket), allowing you
to programmatically control OBS from your Kotlin applications.

## Installation

TODO:

## Usage

Here's a basic example of how to use ktobs with ktor backend:

```kotlin
fun main() = runBlocking {
    val client = HttpClient(CIO) {
        install(WebSockets) {
            pingIntervalMillis = 20_000
            contentConverter = KotlinxWebsocketSerializationConverter(Json)
        }
    }

    // Configure OBS session
    val session = ObsSessionBuilder(client).apply {
        host = "127.0.0.1"
        port = 4455
        password = "your_password"
        eventSubs = ObsEventSub.General + ObsEventSub.Inputs
        // or listen all events
        // eventsSubs = ObsEventSub.All
    }

    // Handle OBS events
    session.onEvent = { event ->
        when (event.eventType) {
            ExitStartedEvent -> println("OBS is shutting down")
            InputCreatedEvent -> {
                val data = event.get<InputCreatedEventData>()
                println("Input was created: ${data.inputName}")
            }
        }
    }

    // Connect and execute commands
    try {
        session.connect {
            // Get list of input kinds
            val kindList = getInputKindList()
            println("Available input kinds: $kindList")

            // Create a new FFmpeg source
            val response = createInput(
                sceneName = "Scene",
                name = "StreamSource",
                kind = "ffmpeg_source",
                settings = JsonObject(mapOf(
                        "input" to JsonPrimitive("your stream host"),
                        "is_local_file" to JsonPrimitive(false)
                    )
                )
            )
        } 
    } catch (e: ObsAuthException) {
        println("Failed to authenticate: $e")
    } catch (e: ObsRequestException) {
        println("Failed to execute request: $e")
    }

    client.close()
}
```

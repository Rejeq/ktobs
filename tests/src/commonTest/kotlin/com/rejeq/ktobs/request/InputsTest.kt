package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.RequestCode
import com.rejeq.ktobs.assertRequestFailsWith
import com.rejeq.ktobs.model.MonitorType
import com.rejeq.ktobs.request.inputs.*
import com.rejeq.ktobs.request.scenes.createScene
import com.rejeq.ktobs.request.scenes.removeScene
import com.rejeq.ktobs.runObsTest
import com.rejeq.ktobs.tryObsRequest
import kotlinx.serialization.json.*
import kotlin.test.*

class InputsTest {
    companion object {
        private const val SCENE_NAME = "test-scene"
        private const val SOURCE_NAME = "test-source"
        private const val NEW_SOURCE_NAME = "renamed-source"
    }

    suspend fun ObsSession.setup() {
        tryObsRequest {
            createScene(SCENE_NAME)
        }
    }

    suspend fun ObsSession.cleanup() {
        tryObsRequest {
            removeScene(SCENE_NAME)
        }
    }

    @Test
    fun testInputs() =
        runObsTest(setup = { setup() }, cleanup = { cleanup() }) {
            val inputKinds = getInputKindList()
            println("Available input kinds: $inputKinds")

            val specialInputs = getSpecialInputs()
            println("Special inputs: $specialInputs")

            val defaultSettings =
                getInputDefaultSettings(
                    kind = "ffmpeg_source",
                )
            println("Default settings: $defaultSettings")

            createInput(
                sceneName = SCENE_NAME,
                name = SOURCE_NAME,
                kind = "ffmpeg_source",
                settings = defaultSettings,
            )

            val inputs = getInputList()
            assertTrue(inputs.any { it.name == SOURCE_NAME })

            val settings = getInputSettings(SOURCE_NAME).settings
            setInputSettings(
                name = SOURCE_NAME,
                settings = settings,
            )

            setInputMute(
                name = SOURCE_NAME,
                muted = true,
            )
            val isMuted = getInputMute(SOURCE_NAME)
            assertTrue(isMuted)

            toggleInputMute(SOURCE_NAME)
            assertFalse(getInputMute(SOURCE_NAME))

            setInputVolume(
                name = SOURCE_NAME,
                mul = 0.5,
            )
            val volume = getInputVolume(SOURCE_NAME)
            assertEquals(0.5, volume.mul)

            setInputAudioBalance(
                name = SOURCE_NAME,
                balance = 0.75,
            )
            val balance = getInputAudioBalance(SOURCE_NAME)
            assertEquals(0.75, balance)

            setInputAudioSyncOffset(
                name = SOURCE_NAME,
                offset = 100,
            )
            val syncOffset = getInputAudioSyncOffset(SOURCE_NAME)
            assertEquals(100, syncOffset)

            setInputAudioMonitorType(
                name = SOURCE_NAME,
                type = MonitorType.MonitorAndOutput,
            )
            val monitorType = getInputAudioMonitorType(SOURCE_NAME)
            assertEquals(MonitorType.MonitorAndOutput, monitorType)

            setInputAudioTracks(
                name = SOURCE_NAME,
                audioTracks =
                    JsonObject(
                        mapOf(
                            "1" to JsonPrimitive(true),
                            "2" to JsonPrimitive(false),
                        ),
                    ),
            )
            val audioTracks = getInputAudioTracks(SOURCE_NAME)
            println("Audio tracks: $audioTracks")

            assertRequestFailsWith(RequestCode.ResourceNotFound) {
                getInputPropertiesListPropertyItems(
                    inputName = SOURCE_NAME,
                    propertyName = "some_property",
                )
            }

            assertRequestFailsWith(RequestCode.ResourceNotFound) {
                pressInputPropertiesButton(
                    inputName = SOURCE_NAME,
                    propertyName = "some_button",
                )
            }

            setInputName(
                inputName = SOURCE_NAME,
                newInputName = NEW_SOURCE_NAME,
            )

            val updatedInputs = getInputList()
            assertTrue(updatedInputs.any { it.name == NEW_SOURCE_NAME })

            removeInput(NEW_SOURCE_NAME)
        }
}

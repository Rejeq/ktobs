package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsTest
import com.rejeq.ktobs.RequestCode
import com.rejeq.ktobs.request.inputs.*
import com.rejeq.ktobs.request.scene.createScene
import com.rejeq.ktobs.request.scene.removeScene
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.*
import kotlin.test.*

class InputsTest : ObsTest() {
    companion object {
        private const val SCENE_NAME = "test-scene"
        private const val SOURCE_NAME = "test-source"
        private const val NEW_SOURCE_NAME = "renamed-source"
    }

    @BeforeTest
    fun init() =
        runBlocking {
            tryObsRequest {
                session.createScene(SCENE_NAME)
            }
        }

    @Test
    fun testInputs() =
        runTest {
            val inputKinds = session.getInputKindList()
            println("Available input kinds: $inputKinds")

            val specialInputs = session.getSpecialInputs()
            println("Special inputs: $specialInputs")

            val defaultSettings =
                session.getInputDefaultSettings(
                    kind = "ffmpeg_source",
                )
            println("Default settings: $defaultSettings")

            session.createInput(
                sceneName = SCENE_NAME,
                name = SOURCE_NAME,
                kind = "ffmpeg_source",
                settings = defaultSettings,
            )

            val inputs = session.getInputList()
            assertTrue(inputs.any { it.name == SOURCE_NAME })

            val settings = session.getInputSettings(SOURCE_NAME).settings
            session.setInputSettings(
                name = SOURCE_NAME,
                settings = settings,
            )

            session.setInputMute(
                name = SOURCE_NAME,
                muted = true,
            )
            val isMuted = session.getInputMute(SOURCE_NAME)
            assertTrue(isMuted)

            session.toggleInputMute(SOURCE_NAME)
            assertFalse(session.getInputMute(SOURCE_NAME))

            session.setInputVolume(
                name = SOURCE_NAME,
                mul = 0.5,
            )
            val volume = session.getInputVolume(SOURCE_NAME)
            assertEquals(0.5, volume.mul)

            session.setInputAudioBalance(
                name = SOURCE_NAME,
                balance = 0.75,
            )
            val balance = session.getInputAudioBalance(SOURCE_NAME)
            assertEquals(0.75, balance)

            session.setInputAudioSyncOffset(
                name = SOURCE_NAME,
                offset = 100,
            )
            val syncOffset = session.getInputAudioSyncOffset(SOURCE_NAME)
            assertEquals(100, syncOffset)

            session.setInputAudioMonitorType(
                name = SOURCE_NAME,
                type = MonitorType.MonitorAndOutput,
            )
            val monitorType = session.getInputAudioMonitorType(SOURCE_NAME)
            assertEquals(MonitorType.MonitorAndOutput, monitorType)

            session.setInputAudioTracks(
                name = SOURCE_NAME,
                audioTracks =
                    JsonObject(
                        mapOf(
                            "1" to JsonPrimitive(true),
                            "2" to JsonPrimitive(false),
                        ),
                    ),
            )
            val audioTracks = session.getInputAudioTracks(SOURCE_NAME)
            println("Audio tracks: $audioTracks")

            assertRequestFailsWith(RequestCode.ResourceNotFound) {
                session.getInputPropertiesListPropertyItems(
                    inputName = SOURCE_NAME,
                    propertyName = "some_property",
                )
            }

            assertRequestFailsWith(RequestCode.ResourceNotFound) {
                session.pressInputPropertiesButton(
                    inputName = SOURCE_NAME,
                    propertyName = "some_button",
                )
            }

            session.setInputName(
                inputName = SOURCE_NAME,
                newInputName = NEW_SOURCE_NAME,
            )

            val updatedInputs = session.getInputList()
            assertTrue(updatedInputs.any { it.name == NEW_SOURCE_NAME })

            session.removeInput(NEW_SOURCE_NAME)
        }

    @AfterTest
    fun cleanup() =
        runBlocking {
            tryObsRequest {
                session.removeScene(SCENE_NAME)
            }
        }
}

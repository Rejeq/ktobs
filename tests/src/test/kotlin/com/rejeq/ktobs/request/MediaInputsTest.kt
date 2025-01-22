package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsTest
import com.rejeq.ktobs.RequestCode
import com.rejeq.ktobs.request.inputs.createInput
import com.rejeq.ktobs.request.mediainputs.*
import com.rejeq.ktobs.request.scene.createScene
import com.rejeq.ktobs.request.scene.removeScene
import com.rejeq.ktobs.request.scene.setCurrentPreviewScene
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlin.test.*

class MediaInputsTest : ObsTest() {
    companion object {
        private const val SCENE_NAME = "test-scene"
        private const val SOURCE_NAME = "test-source"
    }

    @BeforeTest
    fun init() =
        runBlocking {
            tryObsRequest {
                session.createScene(SCENE_NAME)
            }

            tryObsRequest {
                session.setCurrentPreviewScene(SCENE_NAME)
            }

            tryObsRequest {
                session.createInput(
                    sceneName = SCENE_NAME,
                    name = SOURCE_NAME,
                    kind = "image_source",
                )
            }
        }

    @Test
    fun testMediaInputs() =
        runTest {
            val status = session.getMediaInputStatus(SOURCE_NAME)
            println("Media status: $status")

            session.triggerMediaInputAction(
                inputName = SOURCE_NAME,
                mediaAction = MediaAction.Play,
            )

            assertRequestFailsWith(RequestCode.InvalidResourceState) {
                session.setMediaInputCursor(
                    inputName = SOURCE_NAME,
                    mediaCursor = 5000,
                )
            }

            assertRequestFailsWith(RequestCode.InvalidResourceState) {
                session.offsetMediaInputCursor(
                    inputName = SOURCE_NAME,
                    mediaCursorOffset = 2000,
                )
            }
        }

    @AfterTest
    fun cleanup() =
        runBlocking {
            tryObsRequest {
                session.removeScene(SCENE_NAME)
            }
        }
}

package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.RequestCode
import com.rejeq.ktobs.assertRequestFailsWith
import com.rejeq.ktobs.model.MediaAction
import com.rejeq.ktobs.request.inputs.createInput
import com.rejeq.ktobs.request.mediainputs.*
import com.rejeq.ktobs.request.scenes.createScene
import com.rejeq.ktobs.request.scenes.removeScene
import com.rejeq.ktobs.request.scenes.setCurrentPreviewScene
import com.rejeq.ktobs.runObsTest
import com.rejeq.ktobs.tryObsRequest
import kotlin.test.*

class MediaInputsTest {
    companion object {
        private const val SCENE_NAME = "test-scene"
        private const val SOURCE_NAME = "test-source"
    }

    suspend fun ObsSession.setup() {
        tryObsRequest {
            createScene(SCENE_NAME)
        }

        tryObsRequest {
            setCurrentPreviewScene(SCENE_NAME)
        }

        tryObsRequest {
            createInput(
                sceneName = SCENE_NAME,
                name = SOURCE_NAME,
                kind = "image_source",
            )
        }
    }

    suspend fun ObsSession.cleanup() {
        tryObsRequest {
            removeScene(SCENE_NAME)
        }
    }

    @Test
    fun testMediaInputs() =
        runObsTest(setup = { setup() }, cleanup = { cleanup() }) {
            val status = getMediaInputStatus(SOURCE_NAME)
            println("Media status: $status")

            triggerMediaInputAction(
                inputName = SOURCE_NAME,
                mediaAction = MediaAction.Play,
            )

            assertRequestFailsWith(RequestCode.InvalidResourceState) {
                setMediaInputCursor(
                    inputName = SOURCE_NAME,
                    mediaCursor = 5000,
                )
            }

            assertRequestFailsWith(RequestCode.InvalidResourceState) {
                offsetMediaInputCursor(
                    inputName = SOURCE_NAME,
                    mediaCursorOffset = 2000,
                )
            }
        }
}

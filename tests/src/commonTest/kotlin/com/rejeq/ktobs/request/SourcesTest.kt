package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.request.inputs.createInput
import com.rejeq.ktobs.request.scenes.createScene
import com.rejeq.ktobs.request.scenes.removeScene
import com.rejeq.ktobs.request.sources.*
import com.rejeq.ktobs.runObsTest
import com.rejeq.ktobs.tryObsRequest
import kotlin.test.*

class SourcesTest {
    companion object {
        private const val SCENE_NAME = "test-scene"
        private const val SOURCE_NAME = "test-item"
        private const val SCREENSHOT_PATH = "/tmp/test-obs-screenshot.png"
    }

    suspend fun ObsSession.setup() {
        tryObsRequest {
            createScene(SCENE_NAME)
        }

        tryObsRequest {
            createInput(
                sceneName = SCENE_NAME,
                name = SOURCE_NAME,
                kind = "ffmpeg_source",
            )
        }
    }

    suspend fun ObsSession.cleanup() {
        removeScene(SCENE_NAME)
    }

    @Test
    fun testSources() =
        runObsTest(setup = { setup() }, cleanup = { cleanup() }) {
            val active = getSourceActive(SOURCE_NAME)
            println("Is source active: $active")

            getSourceScreenshot(
                sourceName = SCENE_NAME,
                imageFormat = "png",
                imageWidth = 1920,
                imageHeight = 1080,
                imageCompressionQuality = -1,
            )

            saveSourceScreenshot(
                sourceName = SCENE_NAME,
                imageFormat = "png",
                imageFilePath = SCREENSHOT_PATH,
                imageWidth = 1920,
                imageHeight = 1080,
                imageCompressionQuality = -1,
            )
        }
}

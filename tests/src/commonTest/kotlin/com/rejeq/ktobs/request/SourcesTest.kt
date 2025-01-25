package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsTest
import com.rejeq.ktobs.request.inputs.createInput
import com.rejeq.ktobs.request.scene.createScene
import com.rejeq.ktobs.request.scene.removeScene
import com.rejeq.ktobs.request.sources.*
import com.rejeq.ktobs.runBlocking
import kotlinx.coroutines.test.runTest
import kotlin.test.*

class SourcesTest : ObsTest() {
    companion object {
        private const val SCENE_NAME = "test-scene"
        private const val SOURCE_NAME = "test-item"
        private const val SCREENSHOT_PATH = "/tmp/test-obs-screenshot.png"
    }

    @BeforeTest
    fun init() =
        runBlocking {
            tryObsRequest {
                session.createScene(SCENE_NAME)
            }

            tryObsRequest {
                session.createInput(
                    sceneName = SCENE_NAME,
                    name = SOURCE_NAME,
                    kind = "ffmpeg_source",
                )
            }
        }

    @Test
    fun testSources() =
        runTest {
            val active = session.getSourceActive(SOURCE_NAME)
            println("Is source active: $active")

            session.getSourceScreenshot(
                sourceName = SCENE_NAME,
                imageFormat = "png",
                imageWidth = 1920,
                imageHeight = 1080,
                imageCompressionQuality = -1,
            )

            session.saveSourceScreenshot(
                sourceName = SCENE_NAME,
                imageFormat = "png",
                imageFilePath = SCREENSHOT_PATH,
                imageWidth = 1920,
                imageHeight = 1080,
                imageCompressionQuality = -1,
            )
        }

    @AfterTest
    fun cleanup() =
        runBlocking {
            session.removeScene(SCENE_NAME)
        }
}

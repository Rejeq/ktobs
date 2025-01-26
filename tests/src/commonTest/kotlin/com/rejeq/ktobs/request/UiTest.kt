package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsTest
import com.rejeq.ktobs.request.inputs.createInput
import com.rejeq.ktobs.request.scenes.createScene
import com.rejeq.ktobs.request.scenes.removeScene
import com.rejeq.ktobs.request.scenes.setCurrentProgramScene
import com.rejeq.ktobs.request.ui.*
import com.rejeq.ktobs.runBlocking
import kotlinx.coroutines.test.runTest
import kotlin.test.*

class UiTest : ObsTest() {
    companion object {
        private const val SCENE_NAME = "test-scene"
        private const val SOURCE_NAME = "test-source"
    }

    @BeforeTest
    fun init() =
        runBlocking {
            tryObsRequest {
                session.createScene(SCENE_NAME)
                session.setCurrentProgramScene(SCENE_NAME)
                session.createInput(
                    sceneName = SCENE_NAME,
                    name = SOURCE_NAME,
                    kind = "image_source",
                )
            }
        }

    @Test
    fun testUi() =
        runTest {
            val studioEnabled = session.getStudioModeEnabled()
            println("Studio mode enabled: $studioEnabled")

            if (!studioEnabled) {
                session.setStudioModeEnabled(true)
            }

            assertTrue(session.getStudioModeEnabled())

            val monitors = session.getMonitorList()
            println("Monitors: $monitors")

            session.openInputPropertiesDialog(SOURCE_NAME)

            session.openInputFiltersDialog(SOURCE_NAME)

            session.openVideoMixProjector(
                type = VideoMixType.Preview,
                geometry = "0,0,100,100",
            )

            session.openSourceProjector(
                name = SOURCE_NAME,
                geometry = "0,0,100,100",
            )
        }

    @AfterTest
    fun cleanup() =
        runBlocking {
            tryObsRequest {
                session.setStudioModeEnabled(false)
            }

            tryObsRequest {
                session.removeScene(SCENE_NAME)
            }
        }
}

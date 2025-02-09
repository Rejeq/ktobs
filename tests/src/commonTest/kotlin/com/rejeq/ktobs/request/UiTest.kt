package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.model.VideoMixType
import com.rejeq.ktobs.request.inputs.createInput
import com.rejeq.ktobs.request.scenes.createScene
import com.rejeq.ktobs.request.scenes.removeScene
import com.rejeq.ktobs.request.scenes.setCurrentProgramScene
import com.rejeq.ktobs.request.ui.*
import com.rejeq.ktobs.runObsTest
import com.rejeq.ktobs.tryObsRequest
import kotlin.test.*

class UiTest {
    companion object {
        private const val SCENE_NAME = "ui-test-scene"
        private const val SOURCE_NAME = "ui-test-source"
    }

    suspend fun ObsSession.setup() {
        tryObsRequest {
            createScene(SCENE_NAME)
            setCurrentProgramScene(SCENE_NAME)
            createInput(
                sceneName = SCENE_NAME,
                name = SOURCE_NAME,
                kind = "image_source",
            )
        }
    }

    suspend fun ObsSession.cleanup() {
        tryObsRequest {
            setStudioModeEnabled(false)
        }

        tryObsRequest {
            removeScene(SCENE_NAME)
        }
    }

    @Test
    fun testUi() =
        runObsTest(setup = { setup() }, cleanup = { cleanup() }) {
            val studioEnabled = getStudioModeEnabled()
            println("Studio mode enabled: $studioEnabled")

            if (!studioEnabled) {
                setStudioModeEnabled(true)
            }

            assertTrue(getStudioModeEnabled())

            val monitors = getMonitorList()
            println("Monitors: $monitors")

            openInputPropertiesDialog(SOURCE_NAME)

            openInputFiltersDialog(SOURCE_NAME)

            openVideoMixProjector(
                type = VideoMixType.Preview,
                geometry = "0,0,100,100",
            )

            openSourceProjector(
                name = SOURCE_NAME,
                geometry = "0,0,100,100",
            )
        }
}

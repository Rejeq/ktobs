package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.RequestCode
import com.rejeq.ktobs.assertRequestFailsWith
import com.rejeq.ktobs.request.scenes.*
import com.rejeq.ktobs.request.ui.setStudioModeEnabled
import com.rejeq.ktobs.runObsTest
import com.rejeq.ktobs.tryObsRequest
import kotlin.test.*

class SceneTest {
    companion object {
        const val SCENE_NAME = "Test Scene"
        const val SCENE_NEW_NAME = "Renamed Test Scene"
    }

    suspend fun ObsSession.setup() {
        tryObsRequest {
            // Required for [s,g]etSceneSceneTransitionOverride
            setStudioModeEnabled(true)
        }

        tryObsRequest {
            removeScene(SCENE_NAME)
        }

        tryObsRequest {
            removeScene(SCENE_NEW_NAME)
        }
    }

    suspend fun ObsSession.cleanup() {
        tryObsRequest {
            setStudioModeEnabled(false)
        }

        tryObsRequest {
            removeScene(SCENE_NEW_NAME)
        }
    }

    @Test
    fun testScenes() =
        runObsTest(setup = { setup() }, cleanup = { cleanup() }) {
            createScene(SCENE_NAME)

            assertRequestFailsWith(RequestCode.ResourceAlreadyExists) {
                createScene(SCENE_NAME)
            }

            val sceneList = getSceneList()
            assertTrue(sceneList.scenes.any { it.name == SCENE_NAME })

            getGroupList()

            setCurrentPreviewScene(SCENE_NAME)
            val currentPreview = getCurrentPreviewScene()
            assertEquals(SCENE_NAME, currentPreview.name)

            setCurrentProgramScene(SCENE_NAME)

            setSceneSceneTransitionOverride(
                sceneName = SCENE_NAME,
                transitionName = "Fade", // Using a standard OBS transition
                transitionDuration = 1000,
            )

            val transitionOverride =
                getSceneSceneTransitionOverride(
                    SCENE_NAME,
                )
            assertEquals("Fade", transitionOverride.transitionName)
            assertEquals(1000, transitionOverride.transitionDuration)

            setSceneName(SCENE_NAME, null, SCENE_NEW_NAME)
        }
}

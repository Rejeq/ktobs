package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.RequestCode
import com.rejeq.ktobs.request.scenes.*
import com.rejeq.ktobs.request.ui.getStudioModeEnabled
import com.rejeq.ktobs.request.ui.setStudioModeEnabled
import com.rejeq.ktobs.requestCanFailWith
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
            removeScene(SCENE_NAME)
        }

        tryObsRequest {
            removeScene(SCENE_NEW_NAME)
        }
    }

    @Test
    fun testScenes() =
        runObsTest(setup = { setup() }, cleanup = { cleanup() }) {
            val oldSceneList = getSceneList()

            createScene(SCENE_NAME)

            requestCanFailWith(RequestCode.ResourceAlreadyExists) {
                createScene(SCENE_NAME)
            }

            val sceneList = getSceneList()
            assertTrue(sceneList.scenes.any { it.name == SCENE_NAME })

            val groups = getGroupList()
            println("Groups: $groups")

            val mode = getStudioModeEnabled()
            println("Is studio mode enabled: $mode")

            setCurrentPreviewScene(SCENE_NAME)
            val currentPreview = getCurrentPreviewScene()
            println("Current preview: $currentPreview")

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

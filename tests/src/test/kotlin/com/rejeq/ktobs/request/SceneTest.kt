package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsTest
import com.rejeq.ktobs.RequestCode
import com.rejeq.ktobs.request.scene.*
import com.rejeq.ktobs.request.ui.setStudioModeEnabled
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlin.test.*

class SceneTest : ObsTest() {
    companion object {
        const val SCENE_NAME = "Test Scene"
        const val SCENE_NEW_NAME = "Renamed Test Scene"
    }

    @BeforeTest
    fun initTest() =
        runBlocking {
            tryObsRequest {
                // Required for [s,g]etSceneSceneTransitionOverride
                session.setStudioModeEnabled(true)
            }

            tryObsRequest {
                session.removeScene(SCENE_NAME)
            }

            tryObsRequest {
                session.removeScene(SCENE_NEW_NAME)
            }
        }

    @Test
    fun testScenes() =
        runTest {
            session.createScene(SCENE_NAME)

            assertRequestFailsWith(RequestCode.ResourceAlreadyExists) {
                session.createScene(SCENE_NAME)
            }

            val sceneList = session.getSceneList()
            assertTrue(sceneList.scenes.any { it.name == SCENE_NAME })

            session.getGroupList()

            session.setCurrentPreviewScene(SCENE_NAME)
            val currentPreview = session.getCurrentPreviewScene()
            assertEquals(SCENE_NAME, currentPreview.name)

            session.setCurrentProgramScene(SCENE_NAME)

            session.setSceneSceneTransitionOverride(
                sceneName = SCENE_NAME,
                transitionName = "Fade", // Using a standard OBS transition
                transitionDuration = 1000,
            )

            val transitionOverride =
                session.getSceneSceneTransitionOverride(
                    SCENE_NAME,
                )
            assertEquals("Fade", transitionOverride.transitionName)
            assertEquals(1000, transitionOverride.transitionDuration)

            session.setSceneName(SCENE_NAME, null, SCENE_NEW_NAME)
        }

    @AfterTest
    fun cleanup() =
        runBlocking {
            tryObsRequest {
                session.setStudioModeEnabled(false)
            }

            tryObsRequest {
                session.removeScene(SCENE_NEW_NAME)
            }
        }
}

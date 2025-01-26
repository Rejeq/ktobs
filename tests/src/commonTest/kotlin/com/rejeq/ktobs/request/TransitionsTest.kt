package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsTest
import com.rejeq.ktobs.request.scenes.createScene
import com.rejeq.ktobs.request.scenes.removeScene
import com.rejeq.ktobs.request.transitions.*
import com.rejeq.ktobs.request.ui.getStudioModeEnabled
import com.rejeq.ktobs.request.ui.setStudioModeEnabled
import com.rejeq.ktobs.runBlocking
import kotlinx.coroutines.test.runTest
import kotlin.test.*

class TransitionsTest : ObsTest() {
    companion object {
        private const val SCENE_NAME = "test-scene"
        private const val SCENE_NAME_2 = "test-scene-2"
    }

    @BeforeTest
    fun init() =
        runBlocking {
            tryObsRequest {
                session.createScene(SCENE_NAME)
                session.createScene(SCENE_NAME_2)

                if (!session.getStudioModeEnabled()) {
                    session.setStudioModeEnabled(true)
                }
            }
        }

    @Test
    fun testTransitions() =
        runTest {
            val transitionKinds = session.getTransitionKindList()
            println("Available transition kinds: $transitionKinds")

            val transitions = session.getSceneTransitionList()
            println("Available transitions: $transitions")

            val currentTransition = session.getCurrentSceneTransition()
            println("Current transition: $currentTransition")

            session.setCurrentSceneTransition("Cut")

            session.setCurrentSceneTransitionDuration(1000)

            val cursor = session.getCurrentSceneTransitionCursor()
            println("Transition cursor: $cursor")

            session.triggerStudioModeTransition()

            session.setTBarPosition(0.5)
        }

    @AfterTest
    fun cleanup() =
        runBlocking {
            tryObsRequest {
                if (session.getStudioModeEnabled()) {
                    session.setStudioModeEnabled(false)
                }
            }

            tryObsRequest {
                session.removeScene(SCENE_NAME)
                session.removeScene(SCENE_NAME_2)
            }
        }
}

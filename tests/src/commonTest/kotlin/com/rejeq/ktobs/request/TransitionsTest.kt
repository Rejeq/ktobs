package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.request.scenes.createScene
import com.rejeq.ktobs.request.scenes.removeScene
import com.rejeq.ktobs.request.transitions.*
import com.rejeq.ktobs.request.ui.getStudioModeEnabled
import com.rejeq.ktobs.request.ui.setStudioModeEnabled
import com.rejeq.ktobs.runObsTest
import com.rejeq.ktobs.tryObsRequest
import kotlin.test.*

class TransitionsTest {
    companion object {
        private const val SCENE_NAME = "test-scene"
        private const val SCENE_NAME_2 = "test-scene-2"
    }

    suspend fun ObsSession.setup() {
        tryObsRequest {
            createScene(SCENE_NAME)
            createScene(SCENE_NAME_2)

            if (!getStudioModeEnabled()) {
                setStudioModeEnabled(true)
            }
        }
    }

    suspend fun ObsSession.cleanup() {
        tryObsRequest {
            if (getStudioModeEnabled()) {
                setStudioModeEnabled(false)
            }
        }

        tryObsRequest {
            removeScene(SCENE_NAME)
            removeScene(SCENE_NAME_2)
        }
    }

    @Test
    fun testTransitions() =
        runObsTest(setup = { setup() }, cleanup = { cleanup() }) {
            val transitionKinds = getTransitionKindList()
            println("Available transition kinds: $transitionKinds")

            val transitions = getSceneTransitionList()
            println("Available transitions: $transitions")

            val currentTransition = getCurrentSceneTransition()
            println("Current transition: $currentTransition")

            setCurrentSceneTransition("Cut")

            setCurrentSceneTransitionDuration(1000)

            val cursor = getCurrentSceneTransitionCursor()
            println("Transition cursor: $cursor")

            triggerStudioModeTransition()

            setTBarPosition(0.5)
        }
}

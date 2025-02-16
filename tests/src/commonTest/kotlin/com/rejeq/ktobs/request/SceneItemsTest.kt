package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.isVersionSupported
import com.rejeq.ktobs.model.Alignment
import com.rejeq.ktobs.model.BlendMode
import com.rejeq.ktobs.request.inputs.createInput
import com.rejeq.ktobs.request.sceneitems.*
import com.rejeq.ktobs.request.scenes.createScene
import com.rejeq.ktobs.request.scenes.removeScene
import com.rejeq.ktobs.runObsTest
import com.rejeq.ktobs.tryObsRequest
import kotlin.test.*

class SceneItemsTest {
    companion object {
        const val SCENE_NAME = "sceneItem-test-scene"
        const val SOURCE_NAME = "sceneItem-test-source"
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
        tryObsRequest {
            removeScene(SCENE_NAME)
        }
    }

    @Test
    fun testSceneItems() =
        runObsTest(setup = { setup() }, cleanup = { cleanup() }) {
            val itemId =
                createSceneItem(
                    sceneName = SCENE_NAME,
                    sourceName = SOURCE_NAME,
                    enabled = true,
                )

            val items = getSceneItemList(SCENE_NAME)
            println("Items: $items")

            assertEquals(
                Alignment.TOP or Alignment.LEFT,
                items.get(0).transform.alignment,
            )

            val retrievedId =
                getSceneItemId(
                    sceneName = SCENE_NAME,
                    sourceName = SOURCE_NAME,
                    searchOffset = 0,
                )
            println("Scene item id: $retrievedId")

            if (isVersionSupported("5.4.0")) {
                val sourceInfo =
                    getSceneItemSource(
                        sceneName = SCENE_NAME,
                        sceneItemId = itemId,
                    )
                println("Source info is: $sourceInfo")
            }

            val originalTransform =
                getSceneItemTransform(
                    sceneName = SCENE_NAME,
                    sceneItemId = itemId,
                )

            println("Transform is: $originalTransform")

            setSceneItemTransform(
                sceneName = SCENE_NAME,
                sceneItemId = itemId,
                transform =
                    originalTransform.copy(
                        posX = 100.0F,
                        posY = 100.0F,
                        rotation = 45.0F,
                        boundsWidth = 1.0F,
                        boundsHeight = 1.0F,
                    ),
            )

            setSceneItemEnabled(
                sceneName = SCENE_NAME,
                sceneItemId = itemId,
                enabled = false,
            )
            val enabled =
                getSceneItemEnabled(
                    sceneName = SCENE_NAME,
                    sceneItemId = itemId,
                )
            assertFalse(enabled)

            setSceneItemLocked(
                sceneName = SCENE_NAME,
                sceneItemId = itemId,
                locked = true,
            )

            val locked =
                getSceneItemLocked(
                    sceneName = SCENE_NAME,
                    sceneItemId = itemId,
                )
            assertTrue(locked)

            getSceneItemIndex(
                sceneName = SCENE_NAME,
                sceneItemId = itemId,
            )

            setSceneItemIndex(
                sceneName = SCENE_NAME,
                id = itemId,
                index = 0,
            )

            setSceneItemBlendMode(
                sceneName = SCENE_NAME,
                id = itemId,
                mode = BlendMode.Normal,
            )

            val blendMode =
                getSceneItemBlendMode(
                    sceneName = SCENE_NAME,
                    sceneItemId = itemId,
                )
            assertEquals(BlendMode.Normal, blendMode)

            val duplicatedItemId =
                duplicateSceneItem(
                    sceneName = SCENE_NAME,
                    sceneItemId = itemId,
                    destinationSceneName = SCENE_NAME,
                )

            removeSceneItem(
                sceneName = SCENE_NAME,
                itemId = itemId,
            )
            removeSceneItem(
                sceneName = SCENE_NAME,
                itemId = duplicatedItemId,
            )
        }
}

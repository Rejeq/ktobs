package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsTest
import com.rejeq.ktobs.model.Alignment
import com.rejeq.ktobs.model.BlendMode
import com.rejeq.ktobs.request.inputs.createInput
import com.rejeq.ktobs.request.sceneitems.*
import com.rejeq.ktobs.request.scenes.createScene
import com.rejeq.ktobs.request.scenes.removeScene
import com.rejeq.ktobs.runBlocking
import kotlinx.coroutines.test.runTest
import kotlin.test.*

class SceneItemsTest : ObsTest() {
    companion object {
        const val SCENE_NAME = "test-scene"
        const val SOURCE_NAME = "test-source"
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
    fun testSceneItems() =
        runTest {
            val itemId =
                session.createSceneItem(
                    sceneName = SCENE_NAME,
                    sourceName = SOURCE_NAME,
                    enabled = true,
                )

            val items = session.getSceneItemList(SCENE_NAME)
            println("Items: $items")

            assertEquals(
                Alignment.TOP or Alignment.LEFT,
                items.get(0).transform.alignment,
            )

            val retrievedId =
                session.getSceneItemId(
                    sceneName = SCENE_NAME,
                    sourceName = SOURCE_NAME,
                    searchOffset = 0,
                )
            println("Scene item id: $retrievedId")

            val sourceInfo =
                session.getSceneItemSource(
                    sceneName = SCENE_NAME,
                    sceneItemId = itemId,
                )

            println("Source info is: $sourceInfo")

            val originalTransform =
                session.getSceneItemTransform(
                    sceneName = SCENE_NAME,
                    sceneItemId = itemId,
                )

            println("Transform is: $originalTransform")

            session.setSceneItemTransform(
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

            session.setSceneItemEnabled(
                sceneName = SCENE_NAME,
                sceneItemId = itemId,
                enabled = false,
            )
            val enabled =
                session.getSceneItemEnabled(
                    sceneName = SCENE_NAME,
                    sceneItemId = itemId,
                )
            assertFalse(enabled)

            session.setSceneItemLocked(
                sceneName = SCENE_NAME,
                sceneItemId = itemId,
                locked = true,
            )

            val locked =
                session.getSceneItemLocked(
                    sceneName = SCENE_NAME,
                    sceneItemId = itemId,
                )
            assertTrue(locked)

            session.getSceneItemIndex(
                sceneName = SCENE_NAME,
                sceneItemId = itemId,
            )

            session.setSceneItemIndex(
                sceneName = SCENE_NAME,
                id = itemId,
                index = 0,
            )

            session.setSceneItemBlendMode(
                sceneName = SCENE_NAME,
                id = itemId,
                mode = BlendMode.Normal,
            )

            val blendMode =
                session.getSceneItemBlendMode(
                    sceneName = SCENE_NAME,
                    sceneItemId = itemId,
                )
            assertEquals(BlendMode.Normal, blendMode)

            val duplicatedItemId =
                session.duplicateSceneItem(
                    sceneName = SCENE_NAME,
                    sceneItemId = itemId,
                    destinationSceneName = SCENE_NAME,
                )

            session.removeSceneItem(
                sceneName = SCENE_NAME,
                itemId = itemId,
            )
            session.removeSceneItem(
                sceneName = SCENE_NAME,
                itemId = duplicatedItemId,
            )
        }

    @AfterTest
    fun cleanup() =
        runBlocking {
            tryObsRequest {
                session.removeScene(SCENE_NAME)
            }
        }
}

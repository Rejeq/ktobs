@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsTest
import com.rejeq.ktobs.request.filters.*
import com.rejeq.ktobs.request.inputs.createInput
import com.rejeq.ktobs.request.scene.createScene
import com.rejeq.ktobs.request.scene.removeScene
import com.rejeq.ktobs.request.scene.setCurrentPreviewScene
import com.rejeq.ktobs.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.*
import kotlin.test.*

class FiltersTest : ObsTest() {
    companion object {
        private const val SCENE_NAME = "test-scene"
        private const val SOURCE_NAME = "test-source"
        private const val FILTER_NAME = "test-filter"
        private const val NEW_FILTER_NAME = "renamed-filter"
    }

    @BeforeTest
    fun init() =
        runBlocking {
            tryObsRequest {
                session.createScene(SCENE_NAME)
            }

            tryObsRequest {
                session.setCurrentPreviewScene(SCENE_NAME)
            }

            tryObsRequest {
                session.createInput(
                    sceneName = SCENE_NAME,
                    name = SOURCE_NAME,
                    kind = "image_source",
                )
            }
        }

    @Test
    fun testFilters() =
        runTest {
            val filterKinds = session.getSourceFilterKindList()
            println("Available filter kinds: $filterKinds")

            val defaultSettings =
                session.getSourceFilterDefaultSettings(
                    filterKind = "color_filter_v2",
                )
            println("Default filter settings: $defaultSettings")

            session.createSourceFilter(
                sourceName = SOURCE_NAME,
                filterName = FILTER_NAME,
                filterKind = "color_filter_v2",
                filterSettings = defaultSettings,
            )

            val filters = session.getSourceFilterList(SOURCE_NAME)
            assertTrue(filters.any { it.name == FILTER_NAME })

            val filter =
                session.getSourceFilter(
                    sourceName = SOURCE_NAME,
                    filterName = FILTER_NAME,
                )
            assertEquals("color_filter_v2", filter.kind)

            session.setSourceFilterIndex(
                sourceName = SOURCE_NAME,
                filterName = FILTER_NAME,
                index = 0,
            )

            session.setSourceFilterSettings(
                sourceName = SOURCE_NAME,
                filterName = FILTER_NAME,
                settings =
                    JsonObject(
                        mapOf(
                            "brightness" to JsonPrimitive(0.5),
                            "contrast" to JsonPrimitive(1.5),
                        ),
                    ),
            )

            session.setSourceFilterEnabled(
                sourceName = SOURCE_NAME,
                filterName = FILTER_NAME,
                enabled = false,
            )

            session.setSourceFilterName(
                sourceName = SOURCE_NAME,
                filterName = FILTER_NAME,
                newName = NEW_FILTER_NAME,
            )

            val updatedFilters = session.getSourceFilterList(SOURCE_NAME)
            assertTrue(updatedFilters.any { it.name == NEW_FILTER_NAME })

            session.removeSourceFilter(
                sourceName = SOURCE_NAME,
                filterName = NEW_FILTER_NAME,
            )

            val finalFilters = session.getSourceFilterList(SOURCE_NAME)
            assertFalse(finalFilters.any { it.name == NEW_FILTER_NAME })
        }

    @AfterTest
    fun cleanup() =
        runBlocking {
            tryObsRequest {
                session.removeScene(SCENE_NAME)
            }
        }
}

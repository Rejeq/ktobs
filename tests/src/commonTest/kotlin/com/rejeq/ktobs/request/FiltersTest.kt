@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.request.filters.*
import com.rejeq.ktobs.request.inputs.createInput
import com.rejeq.ktobs.request.scenes.createScene
import com.rejeq.ktobs.request.scenes.removeScene
import com.rejeq.ktobs.request.scenes.setCurrentPreviewScene
import com.rejeq.ktobs.runObsTest
import com.rejeq.ktobs.tryObsRequest
import kotlinx.serialization.json.*
import kotlin.test.*

class FiltersTest {
    companion object {
        private const val SCENE_NAME = "filter-test-scene"
        private const val SOURCE_NAME = "filter-test-source"
        private const val FILTER_NAME = "filter-test-filter"
        private const val NEW_FILTER_NAME = "filter-renamed-filter"
    }

    suspend fun ObsSession.setup() {
        tryObsRequest {
            createScene(SCENE_NAME)
        }

        tryObsRequest {
            setCurrentPreviewScene(SCENE_NAME)
        }

        tryObsRequest {
            createInput(
                sceneName = SCENE_NAME,
                name = SOURCE_NAME,
                kind = "image_source",
            )
        }
    }

    suspend fun ObsSession.cleanup() {
        tryObsRequest {
            removeScene(SCENE_NAME)
        }
    }

    @Test
    fun testFilters() =
        runObsTest(setup = { setup() }, cleanup = { cleanup() }) {
            val filterKinds = getSourceFilterKindList()
            println("Available filter kinds: $filterKinds")

            val defaultSettings =
                getSourceFilterDefaultSettings(
                    filterKind = "color_filter_v2",
                )
            println("Default filter settings: $defaultSettings")

            createSourceFilter(
                sourceName = SOURCE_NAME,
                filterName = FILTER_NAME,
                filterKind = "color_filter_v2",
                filterSettings = defaultSettings,
            )

            val filters = getSourceFilterList(SOURCE_NAME)
            assertTrue(filters.any { it.name == FILTER_NAME })

            val filter =
                getSourceFilter(
                    sourceName = SOURCE_NAME,
                    filterName = FILTER_NAME,
                )
            assertEquals("color_filter_v2", filter.kind)

            setSourceFilterIndex(
                sourceName = SOURCE_NAME,
                filterName = FILTER_NAME,
                index = 0,
            )

            setSourceFilterSettings(
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

            setSourceFilterEnabled(
                sourceName = SOURCE_NAME,
                filterName = FILTER_NAME,
                enabled = false,
            )

            setSourceFilterName(
                sourceName = SOURCE_NAME,
                filterName = FILTER_NAME,
                newName = NEW_FILTER_NAME,
            )

            val updatedFilters = getSourceFilterList(SOURCE_NAME)
            assertTrue(updatedFilters.any { it.name == NEW_FILTER_NAME })

            removeSourceFilter(
                sourceName = SOURCE_NAME,
                filterName = NEW_FILTER_NAME,
            )

            val finalFilters = getSourceFilterList(SOURCE_NAME)
            assertFalse(finalFilters.any { it.name == NEW_FILTER_NAME })
        }
}

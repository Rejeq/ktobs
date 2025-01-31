package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.model.DataRealm
import com.rejeq.ktobs.request.config.*
import com.rejeq.ktobs.runObsTest
import com.rejeq.ktobs.tryObsRequest
import kotlinx.serialization.json.*
import kotlin.test.*

class ConfigTest {
    companion object {
        const val PROFILE_NAME = "test-profile"
        const val SCENE_COLLECTION_NAME = "test-scene-collection"
    }

    private var oldProfileName: String? = null
    private var oldSceneCollection: String? = null
    private var oldRecordDir: String? = null

    suspend fun ObsSession.cleanup() {
        oldProfileName?.let {
            tryObsRequest {
                setCurrentProfile(it)
            }
        }

        oldRecordDir?.let {
            tryObsRequest {
                setRecordDirectory(it)
            }
        }

        tryObsRequest {
            removeProfile(PROFILE_NAME)
        }
    }

    @Test
    fun testConfig() =
        runObsTest(cleanup = { cleanup() }) {
            val profiles = getProfileList()
            println("Profiles: $profiles")

            oldProfileName = profiles.currentProfileName
            if (!profiles.profiles.contains(PROFILE_NAME)) {
                println("Creating profile")
                createProfile(PROFILE_NAME)
            }

            val test = getProfileList()
            println("Profiles: $test")

            setCurrentProfile(PROFILE_NAME)

            setProfileParameter(
                category = "Output",
                name = "Mode",
                value = "Simple",
            )

            getProfileParameter(
                category = "Output",
                name = "Mode",
            )

            val scenes = getSceneCollectionList()
            println("Scene collection: ${scenes.collections}")

            oldSceneCollection = scenes.currentName
            if (!scenes.collections.contains(SCENE_COLLECTION_NAME)) {
                createSceneCollection(SCENE_COLLECTION_NAME)
            }
            setCurrentSceneCollection(SCENE_COLLECTION_NAME)

            setPersistentData(
                realm = DataRealm.Profile,
                slotName = "test-slot",
                slotValue = JsonPrimitive("test-value"),
            )

            val persistentData =
                getPersistentData(
                    realm = DataRealm.Profile,
                    slotName = "test-slot",
                )
            println("Persistent data: $persistentData")

            oldRecordDir = getRecordDirectory()
            setRecordDirectory("test-records")

            val service = getStreamServiceSettings()
            println("service settings: ${service.type} -  ${service.settings}")

            setStreamServiceSettings(
                type = "rtmp_common",
                settings =
                    JsonObject(
                        mapOf(
                            "server" to JsonPrimitive("rtmp://test"),
                            "key" to JsonPrimitive("test-key"),
                        ),
                    ),
            )

            val oldVideoSettings = getVideoSettings()
            println("video settings: $oldVideoSettings")
        }
}

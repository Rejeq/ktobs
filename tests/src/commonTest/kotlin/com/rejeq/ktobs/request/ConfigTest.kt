package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsTest
import com.rejeq.ktobs.model.DataRealm
import com.rejeq.ktobs.request.config.*
import com.rejeq.ktobs.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.*
import kotlin.test.*

class ConfigTest : ObsTest() {
    companion object {
        const val PROFILE_NAME = "test-profile"
        const val SCENE_COLLECTION_NAME = "test-scene-collection"
    }

    private var oldProfileName: String? = null
    private var oldSceneCollection: String? = null
    private var oldRecordDir: String? = null

    @Test
    fun testConfig() =
        runTest {
            val profiles = session.getProfileList()
            println("Profiles: $profiles")

            oldProfileName = profiles.currentProfileName
            if (!profiles.profiles.contains(PROFILE_NAME)) {
                println("Craeting profile")
                session.createProfile(PROFILE_NAME)
            }

            val test = session.getProfileList()
            println("Profiles: $test")

            session.setCurrentProfile(PROFILE_NAME)

            session.setProfileParameter(
                category = "Output",
                name = "Mode",
                value = "Simple",
            )

            session.getProfileParameter(
                category = "Output",
                name = "Mode",
            )

            val scenes = session.getSceneCollectionList()
            println("Scene collection: ${scenes.collections}")

            oldSceneCollection = scenes.currentName
            if (!scenes.collections.contains(SCENE_COLLECTION_NAME)) {
                session.createSceneCollection(SCENE_COLLECTION_NAME)
            }
            session.setCurrentSceneCollection(SCENE_COLLECTION_NAME)

            session.setPersistentData(
                realm = DataRealm.Profile,
                slotName = "test-slot",
                slotValue = JsonPrimitive("test-value"),
            )
            val persistentData =
                session.getPersistentData(
                    realm = DataRealm.Profile,
                    slotName = "test-slot",
                )
            println("Persistent data: $persistentData")

            oldRecordDir = session.getRecordDirectory()
            session.setRecordDirectory("test-records")

            val service = session.getStreamServiceSettings()
            println("service settings: ${service.type} -  ${service.settings}")

            session.setStreamServiceSettings(
                type = "rtmp_common",
                settings =
                    JsonObject(
                        mapOf(
                            "server" to JsonPrimitive("rtmp://test"),
                            "key" to JsonPrimitive("test-key"),
                        ),
                    ),
            )

            val oldVideoSettings = session.getVideoSettings()
            println("video settings: $oldVideoSettings")
        }

    @AfterTest
    fun cleanup() =
        runBlocking {
            oldProfileName?.let {
                tryObsRequest {
                    session.setCurrentProfile(it)
                }
            }

            oldRecordDir?.let {
                tryObsRequest {
                    session.setRecordDirectory(it)
                }
            }

            tryObsRequest {
                session.removeProfile(PROFILE_NAME)
            }
        }
}

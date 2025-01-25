package com.rejeq.ktobs.request

import com.rejeq.ktobs.ObsTest
import com.rejeq.ktobs.RequestCode
import com.rejeq.ktobs.request.general.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.*
import kotlin.test.*

class GeneralTest : ObsTest() {
    @Test
    fun testGeneral() =
        runTest {
            session.getVersion()
            session.getStats()
            session.getHotkeyList()

            session.broadcastCustomEvent(
                JsonObject(
                    mapOf("testKey" to JsonPrimitive("testValue")),
                ),
            )

            assertRequestFailsWith(RequestCode.ResourceNotFound) {
                session.triggerHotkeyByName("test-key")
            }

            assertRequestFailsWith(RequestCode.ResourceNotFound) {
                session.triggerHotkeyByKeySequence(
                    keyId = "test-key",
                    keyModifiers =
                        KeyModifiers(
                            shift = true,
                            control = true,
                            alt = false,
                            command = false,
                        ),
                )
            }

            assertRequestFailsWith(RequestCode.ResourceNotFound) {
                session.callVendorRequest(
                    vendorName = "test-vendor",
                    requestType = "test-request",
                    requestData =
                        JsonObject(
                            mapOf(
                                "test" to JsonPrimitive("data"),
                            ),
                        ),
                )
            }
        }
}

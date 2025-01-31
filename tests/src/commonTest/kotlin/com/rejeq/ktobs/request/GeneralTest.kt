package com.rejeq.ktobs.request

import com.rejeq.ktobs.RequestCode
import com.rejeq.ktobs.assertRequestFailsWith
import com.rejeq.ktobs.model.KeyModifiers
import com.rejeq.ktobs.request.general.*
import com.rejeq.ktobs.runObsTest
import kotlinx.serialization.json.*
import kotlin.test.*

class GeneralTest {
    @Test
    fun testGeneral() =
        runObsTest {
            getVersion()
            getStats()
            getHotkeyList()

            broadcastCustomEvent(
                JsonObject(
                    mapOf("testKey" to JsonPrimitive("testValue")),
                ),
            )

            assertRequestFailsWith(RequestCode.ResourceNotFound) {
                triggerHotkeyByName("test-key")
            }

            assertRequestFailsWith(RequestCode.ResourceNotFound) {
                triggerHotkeyByKeySequence(
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
                callVendorRequest(
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

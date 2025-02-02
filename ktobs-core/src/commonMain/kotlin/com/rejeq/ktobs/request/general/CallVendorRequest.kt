package com.rejeq.ktobs.request.general

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
class CallVendorRequestRequest(
    val vendorName: String,
    val requestType: String,
    val requestData: JsonElement? = null,
)

@Serializable
data class CallVendorRequestResponse(
    val vendorName: String,
    val requestType: String,
    val responseData: JsonElement? = null,
)

// Sends a request registered to a vendor request
//
// A vendor is a unique name registered by a third-party plugin or script, which
// allows for custom requests and events to be added to obs-websocket. If a
// plugin or script implements vendor requests or events, documentation is
// expected to be provided with them.
suspend fun ObsSession.callVendorRequest(
    vendorName: String,
    requestType: String,
    requestData: JsonElement? = null,
): CallVendorRequestResponse =
    callMethod(
        "CallVendorRequest",
        CallVendorRequestRequest(vendorName, requestType, requestData),
    )

package com.rejeq.ktobs.event.general

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

/** OBS has begun the shutdown process */
const val ExitStartedEvent = "ExitStarted"

/**
 * An event has been emitted from a vendor.
 *
 * A vendor is a unique name registered by a third-party plugin or script, which
 * allows for custom requests and events to be added to obs-websocket. If a
 * plugin or script implements vendor requests or events, documentation is
 * expected to be provided with them.
 */
const val VendorEvent = "VendorEvent"

@Serializable
class VendorEventData(
    /** Name of the vendor emitting the event */
    val vendorName: String,
    /** Vendor-provided event typedef */
    val eventType: String,
    /** Vendor-provided event data */
    val eventData: JsonObject? = null,
)

/** Custom event emitted by BroadcastCustomEvent */
const val CustomEvent = "CustomEvent"

@Serializable
class CustomEventData(
    /** Custom event data */
    val eventData: JsonObject? = null,
)

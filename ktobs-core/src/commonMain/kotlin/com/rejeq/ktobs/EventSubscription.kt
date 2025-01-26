package com.rejeq.ktobs

import kotlin.jvm.JvmInline

/**
 * Represents a set of obs event subscriptions.
 * Provides bitwise operations to combine and manage event subscriptions.
 *
 * @property value The bitmask representing active event subscriptions
 */
@JvmInline
value class ObsEventSubs(
    val value: Int = 0,
) {
    /**
     * Checks if this subscription set contains the specified subscriptions.
     *
     * @param subs The subscriptions to check for
     * @return true if all specified subscriptions are present
     */
    operator fun contains(subs: ObsEventSubs): Boolean =
        if (subs.value == 0) value == 0 else (value and subs.value) != 0

    /**
     * Combines this subscription set with another.
     *
     * @param subs The subscriptions to add
     * @return A new [ObsEventSubs] containing all subscriptions from both sets
     */
    operator fun plus(subs: ObsEventSubs): ObsEventSubs =
        ObsEventSubs(value or subs.value)

    /**
     * Removes specified subscriptions from this set
     *
     * @param subs The subscriptions to remove
     * @return A new [ObsEventSubs] with the specified subscriptions removed
     */
    operator fun minus(subs: ObsEventSubs): ObsEventSubs =
        ObsEventSubs(value and subs.value.inv())
}

/**
 * Defines all possible OBS WebSocket event subscription types.
 * Each constant represents a specific category of events that can be subscribed
 * to.
 */
object ObsEventSub {
    /** Used to disable all events */
    val None = ObsEventSubs(0)
    val General = ObsEventSubs(1 shl 0)
    val Config = ObsEventSubs(1 shl 1)
    val Scenes = ObsEventSubs(1 shl 2)
    val Inputs = ObsEventSubs(1 shl 3)
    val Transitions = ObsEventSubs(1 shl 4)
    val Filters = ObsEventSubs(1 shl 5)
    val Outputs = ObsEventSubs(1 shl 6)
    val SceneItems = ObsEventSubs(1 shl 7)
    val MediaInputs = ObsEventSubs(1 shl 8)
    val Vendors = ObsEventSubs(1 shl 9)
    val Ui = ObsEventSubs(1 shl 10)

    /** Helper to receive all non-high-volume events */
    val All = ObsEventSubs((1 shl 11) - 1)

    val InputVolumeMeters = ObsEventSubs(1 shl 16)
    val InputActiveStateChanged = ObsEventSubs(1 shl 17)
    val InputShowStateChanged = ObsEventSubs(1 shl 18)
    val SceneItemTransformChanged = ObsEventSubs(1 shl 19)
}

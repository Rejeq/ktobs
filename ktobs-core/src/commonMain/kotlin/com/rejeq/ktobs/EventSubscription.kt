package com.rejeq.ktobs

/**
 * Base class for managing event subscriptions.
 * Provides bitwise operations to combine and manage event subscriptions.
 *
 * @property value The bitmask representing active event subscriptions
 */
open class ObsEventSubs(
    val value: Int = 0,
) {
    /**
     * Checks if this subscription set contains the specified subscriptions.
     *
     * @param subs The subscriptions to check for
     * @return true if all specified subscriptions are present
     */
    fun has(subs: ObsEventSubs): Boolean =
        if (subs.value == 0) {
            value == 0
        } else {
            (value and subs.value) != 0
        }

    /**
     * Combines this subscription set with another.
     *
     * @param subs The subscriptions to add
     * @return A new [ObsEventSubs] containing all subscriptions from both sets
     */
    infix fun and(subs: ObsEventSubs): ObsEventSubs =
        ObsEventSubs(value or subs.value)

    /**
     * Removes specified subscriptions from this set
     *
     * @param subs The subscriptions to remove
     * @return A new [ObsEventSubs] with the specified subscriptions removed
     */
    infix fun del(subs: ObsEventSubs): ObsEventSubs =
        ObsEventSubs(value and subs.value.inv())
}

/**
 * Defines all possible OBS WebSocket event subscription types.
 * Each type represents a specific category of events that can be subscribed to.
 *
 * @property value The bitmask value for this subscription type
 */
sealed class ObsEventSub(
    value: Int,
) : ObsEventSubs(value) {
    /** Used to disable all events. */
    class None : ObsEventSub(0)

    class General : ObsEventSub(1 shl 0)

    class Config : ObsEventSub(1 shl 1)

    class Scenes : ObsEventSub(1 shl 2)

    class Inputs : ObsEventSub(1 shl 3)

    class Transitions : ObsEventSub(1 shl 4)

    class Filters : ObsEventSub(1 shl 5)

    class Outputs : ObsEventSub(1 shl 6)

    class SceneItems : ObsEventSub(1 shl 7)

    class MediaInputs : ObsEventSub(1 shl 8)

    class Vendors : ObsEventSub(1 shl 9)

    class Ui : ObsEventSub(1 shl 10)

    /** Helper to receive all non-high-volume events. */
    class All : ObsEventSub((1 shl 11) - 1)

    class InputVolumeMeters : ObsEventSub(1 shl 16)

    class InputActiveStateChanged : ObsEventSub(1 shl 17)

    class InputShowStateChanged : ObsEventSub(1 shl 18)

    class SceneItemTransformChanged : ObsEventSub(1 shl 19)
}

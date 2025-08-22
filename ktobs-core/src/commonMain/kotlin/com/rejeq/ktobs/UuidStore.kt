package com.rejeq.ktobs

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * A store that associates values of type [T] with sequentially generated
 * [Uuid] keys.
 *
 * **Thread-safety:**
 * This implementation is **not** thread-safe. If used across multiple threads,
 * external synchronization is required.
 *
 * @param T The type of values stored.
 */
@OptIn(ExperimentalUuidApi::class)
fun <T> mutableUuidStore(): UuidStore<T> = UuidStore()

/**
 * @param currUuid The UUID to start counting from.
 *        Useful for testing.
 * @param maxValue The maximum value allowed for each unsigned 64-bit component
 *        of the UUID.
 *        Useful for testing.
 * @param store The initial mutable map.
 *        Useful for testing.
 */
@OptIn(ExperimentalUuidApi::class)
class UuidStore<T> internal constructor(
    private var currUuid: Uuid = Uuid.fromLongs(0, 0),
    private val maxValue: ULong = ULong.MAX_VALUE,
    private val store: MutableMap<Uuid, T> = mutableMapOf(),
) : Map<Uuid, T> by store {
    /**
     * Generates the unique [Uuid] and associates it with the given [value].
     *
     * @param value The value to store in the map.
     * @return The newly generated [Uuid] that is associated with [value].
     * @throws UnableAcquireUuidException if unable to generate unique UUID
     */
    fun register(value: T): Uuid {
        val next = increment(currUuid)
        if (next in store) {
            throw UnableAcquireUuidException()
        }

        currUuid = next
        store[next] = value

        return next
    }

    /**
     * Removes the value associated with the given [uuid].
     *
     * If the [uuid] is not present, this method does nothing.
     *
     * @param uuid The UUID whose associated value should be removed.
     * @return The previous value associated with this key
     *         or `null` if the key was not present in the map.
     */
    fun remove(uuid: Uuid): T? = store.remove(uuid)

    /**
     * Computes the next UUID based on [uuid] by incrementing its components.
     *
     * Wraps around to the beginning if the maximum value is reached.
     *
     * @param uuid The current UUID to increment.
     * @return The next sequential UUID.
     */
    private fun increment(uuid: Uuid): Uuid =
        uuid.toULongs { most, least ->
            val (newMost, newLeast) =
                when {
                    most == maxValue && least == maxValue -> {
                        // Start from the beginning, since likely this state
                        // already removed
                        0UL to 1UL
                    }
                    least == maxValue -> {
                        (most + 1UL) to 0UL
                    }
                    else -> {
                        most to (least + 1UL)
                    }
                }

            Uuid.fromULongs(newMost, newLeast)
        }
}

/**
 * Exception thrown when [UuidStore] is unable to generate a unique UUID.
 *
 * This can only occur if the store cycles through all `2^128` possible UUID
 * values and reaches the minimum uuid value, that is not removed, which is
 * practically impossible under normal usage.
 */
class UnableAcquireUuidException : Exception("Unable to generate unique uuid")

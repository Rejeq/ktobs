package com.rejeq.ktobs

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class UuidStoreTest {
    @Test
    fun testRegisterGeneratesSequentialIds() {
        val store = mutableUuidStore<Unit>()

        val id1 = store.register(Unit)
        val id2 = store.register(Unit)

        assertTrue(id2 > id1)
    }

    @Test
    fun testFirstIdFromDefaultStartIsZeroOne() {
        val store = mutableUuidStore<Unit>()

        val id = store.register(Unit)

        id.toULongs { most, least ->
            assertEquals(0UL, most)
            assertEquals(1UL, least)
        }
    }

    @Test
    fun testUniqueIdsAcrossManyAllocations() {
        val store = mutableUuidStore<Unit>()
        val count = 1000

        val ids =
            buildList {
                repeat(count) { add(store.register(Unit)) }
            }

        assertEquals(count, ids.toSet().size)
    }

    @Test
    fun testMonotonicIncreaseAcrossMany() {
        val store = mutableUuidStore<Unit>()

        var previous = store.register(Unit)
        repeat(200) {
            val current = store.register(Unit)
            assertTrue(current > previous)
            previous = current
        }
    }

    @Test
    fun testRegisterStoreValues() {
        val store = mutableUuidStore<String>()
        val id = store.register("a")

        assertEquals("a", store[id])
    }

    @Test
    fun testRemoveWorksCorrectly() {
        val store = mutableUuidStore<Unit>()
        val id = store.register(Unit)

        store.remove(id)
        assertNull(store[id])
    }

    @Test
    fun testRemoveNonexistentDoesNothing() {
        val store = mutableUuidStore<String>()
        val id = store.register("a")
        val sizeBefore = store.toList().size

        val unknown = Uuid.fromULongs(0UL, 999UL)
        store.remove(unknown)

        val sizeAfter = store.toList().size
        assertEquals(sizeBefore, sizeAfter)
        assertEquals("a", store[id])
    }

    @Test
    fun testRemoveCurrentIdDoesNotResetSequence() {
        val store = mutableUuidStore<Unit>()

        val id1 = store.register(Unit) // (0,1)
        val id2 = store.register(Unit) // (0,2) current

        store.remove(id2)

        val id3 = store.register(Unit) // expect (0,3) not reusing id2
        id3.toULongs { most, least ->
            assertEquals(0UL, most)
            assertEquals(3UL, least)
        }

        // Ensure id2 is still absent
        assertNull(store[id2])
        // Ensure id1 remains present
        assertEquals(Unit, store[id1])
    }

    @Test
    fun testGetUnknownUuidReturnsNull() {
        val store = mutableUuidStore<Unit>()
        val unknown = Uuid.fromULongs(0UL, 5UL)
        assertNull(store[unknown])
    }

    @Test
    fun testIteratesAllValues() {
        val store = mutableUuidStore<String>()
        store.register("a")
        store.register("b")
        store.register("c")

        val values = store.values
        assertEquals(listOf("a", "b", "c"), values.sorted())
    }

    @Test
    fun testEmptyStoreHasNoElements() {
        val store = mutableUuidStore<Unit>()
        val values = store.toList()
        assertTrue(values.isEmpty())
    }

    @Test
    fun testWrapsLeastPart() {
        val startUuid = Uuid.fromULongs(10UL, ULong.MAX_VALUE)

        val store = UuidStore<Unit>(startUuid)
        val incremented = store.register(Unit)

        incremented.toULongs { most, least ->
            assertEquals(11UL, most)
            assertEquals(0UL, least)
        }
    }

    @Test
    fun testWrapsBothParts() {
        val startUuid = Uuid.fromULongs(ULong.MAX_VALUE, ULong.MAX_VALUE)

        val store = UuidStore<Unit>(startUuid)
        val incremented = store.register(Unit)

        incremented.toULongs { most, least ->
            assertEquals(0UL, most)
            assertEquals(1UL, least)
        }
    }

    @Test
    fun testIncrementNearLeastMaxThenWrapsMost() {
        val startUuid = Uuid.fromULongs(0UL, ULong.MAX_VALUE - 1UL)
        val store = UuidStore<Unit>(startUuid)

        val first = store.register(Unit) // expect (0, MAX)
        val second = store.register(Unit) // expect (1, 0)

        first.toULongs { most, least ->
            assertEquals(0UL, most)
            assertEquals(ULong.MAX_VALUE, least)
        }
        second.toULongs { most, least ->
            assertEquals(1UL, most)
            assertEquals(0UL, least)
        }
    }

    @Test
    fun testDoubleWrapFromEdge() {
        val startUuid = Uuid.fromULongs(ULong.MAX_VALUE, ULong.MAX_VALUE - 1UL)
        val store = UuidStore<Unit>(startUuid)

        val first = store.register(Unit) // (MAX, MAX)
        val second = store.register(Unit) // wraps to (0, 1)

        first.toULongs { most, least ->
            assertEquals(ULong.MAX_VALUE, most)
            assertEquals(ULong.MAX_VALUE, least)
        }
        second.toULongs { most, least ->
            assertEquals(0UL, most)
            assertEquals(1UL, least)
        }
    }

    @Test
    fun testThrowsError() {
        val store = UuidStore<Unit>(maxValue = 1UL)
        store.register(Unit) // id = 01
        store.register(Unit) // id = 10
        store.register(Unit) // id = 11

        assertFailsWith<UnableAcquireUuidException> {
            store.register(Unit)
        }
    }

    @Test
    fun testNotThrowsErrorIfValueRemoved() {
        val store = UuidStore<Unit>(maxValue = 1UL)
        val first = store.register(Unit) // id = 01
        store.register(Unit) // id = 10
        store.register(Unit) // id = 11

        store.remove(first)

        val second = store.register(Unit)
        assertEquals(first, second)
    }
}

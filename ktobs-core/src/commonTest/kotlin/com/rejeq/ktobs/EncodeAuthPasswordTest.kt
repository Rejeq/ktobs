package com.rejeq.ktobs

import kotlin.test.Test
import kotlin.test.assertEquals

class EncodeAuthPasswordTest {
    @Test
    fun testValidInput() {
        val auth =
            Authentication(challenge = "testChallenge", salt = "testSalt")
        val password = "testPassword"
        val expected = "rcRjlBlE8riU19OFxLhbQRBpKE0GN9wko+867TDybHc="

        val encoded = encodeAuthPassword(auth, password)

        assertEquals(expected, encoded)
    }

    @Test
    fun testEmptyPassword() {
        val auth =
            Authentication(challenge = "testChallenge", salt = "testSalt")
        val password = ""
        val expected = "pooPXKh2JPsVchHxQaRKKxDl9lFq4IdEc7GVTUengLU="

        val encoded = encodeAuthPassword(auth, password)

        assertEquals(expected, encoded)
    }

    @Test
    fun testEmptyAuthentication() {
        val auth = Authentication(challenge = "", salt = "")
        val password = "pass"
        val expected = "51ZpJuejkALTsUxx4+X77mm9elKIi+i6+wq8EZNkqT0="

        val encoded = encodeAuthPassword(auth, password)

        assertEquals(expected, encoded)
    }

    @Test
    fun testUnicodeCharacters() {
        val auth = Authentication(challenge = "🔒", salt = "🧂")
        val password = "💻"
        val expected = "hNsfXRI/5H9KlqBvwuS2LEwwy6H+WO/QFgKCgRlAzSg="

        val encoded = encodeAuthPassword(auth, password)

        assertEquals(expected, encoded)
    }
}

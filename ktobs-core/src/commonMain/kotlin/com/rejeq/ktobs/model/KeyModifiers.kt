package com.rejeq.ktobs.model

import kotlinx.serialization.Serializable

@Serializable
data class KeyModifiers(
    val shift: Boolean? = null,
    val control: Boolean? = null,
    val alt: Boolean? = null,
    val command: Boolean? = null,
)

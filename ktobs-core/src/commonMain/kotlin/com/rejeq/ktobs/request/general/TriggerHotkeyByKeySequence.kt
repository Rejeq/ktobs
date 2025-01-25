package com.rejeq.ktobs.request.general

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class KeyModifiers(
    val shift: Boolean? = null,
    val control: Boolean? = null,
    val alt: Boolean? = null,
    val command: Boolean? = null,
)

@Serializable
data class TriggerHotkeyByKeySequenceRequest(
    val keyId: String? = null,
    val keyModifiers: KeyModifiers? = null,
)

// Triggers a hotkey using a sequence of keys
suspend fun ObsSession.triggerHotkeyByKeySequence(
    keyId: String? = null,
    keyModifiers: KeyModifiers? = null,
) {
    callUnitMethod(
        "TriggerHotkeyByKeySequence",
        TriggerHotkeyByKeySequenceRequest(keyId, keyModifiers),
    )
}

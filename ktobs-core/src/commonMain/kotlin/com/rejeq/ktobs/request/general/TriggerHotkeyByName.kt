package com.rejeq.ktobs.request.general

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TriggerHotkeyByNameRequest(
    @SerialName("hotkeyName") val name: String,
)

// Triggers a hotkey using its name
suspend fun ObsSession.triggerHotkeyByName(name: String) {
    callUnitMethod("TriggerHotkeyByName", TriggerHotkeyByNameRequest(name))
}

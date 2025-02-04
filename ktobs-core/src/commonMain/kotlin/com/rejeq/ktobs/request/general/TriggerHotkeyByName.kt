package com.rejeq.ktobs.request.general

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TriggerHotkeyByNameRequest(
    @SerialName("hotkeyName") val name: String,
)

/**
 * Triggers a hotkey using its name.
 *
 * Note: Hotkey functionality in obs-websocket comes as-is, and we do not
 * guarantee support if things are broken. In 9/10 usages of hotkey requests,
 * there exists a better, more reliable method via other requests.
 *
 * @param name Name of the hotkey to trigger
 */
suspend fun ObsSession.triggerHotkeyByName(name: String) {
    callUnitMethod("TriggerHotkeyByName", TriggerHotkeyByNameRequest(name))
}

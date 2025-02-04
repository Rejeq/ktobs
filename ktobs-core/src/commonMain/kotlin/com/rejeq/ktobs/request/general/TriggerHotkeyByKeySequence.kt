package com.rejeq.ktobs.request.general

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import com.rejeq.ktobs.model.KeyModifiers
import kotlinx.serialization.Serializable

@Serializable
class TriggerHotkeyByKeySequenceRequest(
    val keyId: String? = null,
    val keyModifiers: KeyModifiers? = null,
)

/**
 * Triggers a hotkey using a sequence of keys.
 *
 * Note: Hotkey functionality in obs-websocket comes as-is, and we do not
 * guarantee support if things are broken. In 9/10 usages of hotkey requests,
 * there exists a better, more reliable method via other requests.
 *
 * @param keyId Key identifier to use
 * @param keyModifiers Key modifiers to apply
 */
suspend fun ObsSession.triggerHotkeyByKeySequence(
    keyId: String? = null,
    keyModifiers: KeyModifiers? = null,
) {
    callUnitMethod(
        "TriggerHotkeyByKeySequence",
        TriggerHotkeyByKeySequenceRequest(keyId, keyModifiers),
    )
}

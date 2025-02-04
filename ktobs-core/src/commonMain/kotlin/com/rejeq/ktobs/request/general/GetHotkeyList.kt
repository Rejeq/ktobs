package com.rejeq.ktobs.request.general

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GetHotkeyListResponse(
    @SerialName("hotkeys") val hotkeys: List<String>,
)

/**
 * Gets an array of all hotkey names in OBS.
 *
 * Note: Hotkey functionality in obs-websocket comes as-is, and we do not
 * guarantee support if things are broken. In 9/10 usages of hotkey requests,
 * there exists a better, more reliable method via other requests
 * 
 * @return Array of all hotkey names
 */
suspend fun ObsSession.getHotkeyList(): List<String> =
    callMethod<GetHotkeyListResponse>("GetHotkeyList").hotkeys

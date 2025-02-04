package com.rejeq.ktobs.request.mediainputs

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import com.rejeq.ktobs.model.MediaAction
import kotlinx.serialization.Serializable

@Serializable
class TriggerMediaInputActionRequest(
    val inputName: String? = null,
    val inputUuid: String? = null,
    val mediaAction: MediaAction,
)

/**
 * Triggers an action on a media input.
 *
 * @param inputName Name of the media input
 * @param inputUuid UUID of the media input
 * @param mediaAction Identifier of the media action to trigger
 */
suspend fun ObsSession.triggerMediaInputAction(
    inputName: String? = null,
    inputUuid: String? = null,
    mediaAction: MediaAction,
) = callUnitMethod(
    "TriggerMediaInputAction",
    TriggerMediaInputActionRequest(inputName, inputUuid, mediaAction),
)

package com.rejeq.ktobs.request.ui

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class OpenInputInteractDialogRequest(
    @SerialName("inputName") val name: String? = null,
    @SerialName("inputUuid") val uuid: String? = null,
)

// Opens the interact dialog of an input
suspend fun ObsSession.openInputInteractDialog(
    name: String? = null,
    uuid: String? = null,
) = callUnitMethod(
    "OpenInputInteractDialog",
    OpenInputInteractDialogRequest(name, uuid),
)

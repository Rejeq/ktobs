package com.rejeq.ktobs.request.ui

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OpenInputFiltersDialogRequest(
    @SerialName("inputName") val name: String? = null,
    @SerialName("inputUuid") val uuid: String? = null,
)

// Opens the filters dialog of an input
suspend fun ObsSession.openInputFiltersDialog(
    name: String? = null,
    uuid: String? = null,
) = callUnitMethod(
    "OpenInputFiltersDialog",
    OpenInputFiltersDialogRequest(name, uuid),
)

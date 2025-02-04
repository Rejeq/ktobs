package com.rejeq.ktobs.request.ui

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class OpenInputFiltersDialogRequest(
    @SerialName("inputName") val name: String? = null,
    @SerialName("inputUuid") val uuid: String? = null,
)

/**
 * Opens the filters dialog of an input.
 *
 * @param name Name of the input to open the dialog of
 * @param uuid UUID of the input to open the dialog of
 */
suspend fun ObsSession.openInputFiltersDialog(
    name: String? = null,
    uuid: String? = null,
) = callUnitMethod(
    "OpenInputFiltersDialog",
    OpenInputFiltersDialogRequest(name, uuid),
)

package com.rejeq.ktobs.request.ui

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class OpenSourceProjectorRequest(
    val sourceName: String? = null,
    val sourceUuid: String? = null,
    val monitorIndex: Int? = null,
    val projectorGeometry: String? = null,
)

/**
 * Opens a projector for a source.
 *
 * @param name Name of the source to open a projector for
 * @param uuid UUID of the source to open a projector for
 * @param monitorIndex Monitor index,
 *        use GetMonitorList to obtain index
 *        use -1 for windowed mode
 * @param projectorGeometry Size/Position data for a windowed projector as QRect
 *        string. Mutually exclusive with monitorIndex
 */
suspend fun ObsSession.openSourceProjector(
    name: String? = null,
    uuid: String? = null,
    monitorIndex: Int? = null,
    geometry: String? = null,
) = callUnitMethod(
    "OpenSourceProjector",
    OpenSourceProjectorRequest(name, uuid, monitorIndex, geometry),
)

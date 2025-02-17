package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import com.rejeq.ktobs.model.DataRealm
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
class GetPersistentDataRequest(
    val realm: DataRealm,
    val slotName: String,
)

@Serializable
class GetPersistentDataResponse(
    /** Value associated with the slot. null if not set */
    val slotValue: JsonElement?,
)

/**
 * Gets the value of a "slot" from the selected persistent data realm.
 *
 * @param realm The data realm to select.
 * @param slotName The name of the slot to retrieve data from
 * @return Value associated with the slot
 */
suspend fun ObsSession.getPersistentData(
    realm: DataRealm,
    slotName: String,
): JsonElement? =
    callMethod<GetPersistentDataResponse, GetPersistentDataRequest>(
        "GetPersistentData",
        GetPersistentDataRequest(realm, slotName),
    ).slotValue

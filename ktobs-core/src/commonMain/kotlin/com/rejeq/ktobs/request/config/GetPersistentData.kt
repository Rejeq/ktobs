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
    val slotValue: JsonElement?,
)

// Gets the value of a "slot" from the selected persistent data realm
suspend fun ObsSession.getPersistentData(
    realm: DataRealm,
    slotName: String,
): JsonElement? =
    callMethod<GetPersistentDataResponse, GetPersistentDataRequest>(
        "GetPersistentData",
        GetPersistentDataRequest(realm, slotName),
    ).slotValue

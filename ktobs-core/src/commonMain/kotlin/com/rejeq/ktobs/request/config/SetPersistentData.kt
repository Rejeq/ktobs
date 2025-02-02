package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import com.rejeq.ktobs.model.DataRealm
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
class SetPersistentDataRequest(
    val realm: DataRealm,
    val slotName: String,
    val slotValue: JsonElement,
)

/**
 * Sets the value of a "slot" from the selected persistent data realm.
 *
 * @param realm The data realm to select
 * @param slotName The name of the slot to retrieve data from
 * @param slotValue The value to apply to the slot
 */
suspend fun ObsSession.setPersistentData(
    realm: DataRealm,
    slotName: String,
    slotValue: JsonElement,
) = callUnitMethod(
    "SetPersistentData",
    SetPersistentDataRequest(realm, slotName, slotValue),
)

package com.rejeq.ktobs.request.config

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
enum class DataRealm {
    @SerialName("OBS_WEBSOCKET_DATA_REALM_GLOBAL")
    Global,

    @SerialName("OBS_WEBSOCKET_DATA_REALM_PROFILE")
    Profile,
}

@Serializable
data class SetPersistentDataRequest(
    val realm: DataRealm,
    val slotName: String,
    val slotValue: JsonElement,
)

// Sets the value of a "slot" from the selected persistent data realm
suspend fun ObsSession.setPersistentData(
    realm: DataRealm,
    slotName: String,
    slotValue: JsonElement,
) = callUnitMethod(
    "SetPersistentData",
    SetPersistentDataRequest(realm, slotName, slotValue),
)

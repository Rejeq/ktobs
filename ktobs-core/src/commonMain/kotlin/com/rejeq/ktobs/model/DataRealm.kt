package com.rejeq.ktobs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class DataRealm {
    @SerialName("OBS_WEBSOCKET_DATA_REALM_GLOBAL")
    Global,

    @SerialName("OBS_WEBSOCKET_DATA_REALM_PROFILE")
    Profile,
}

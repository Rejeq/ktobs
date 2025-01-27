package com.rejeq.ktobs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MonitorType {
    @SerialName("OBS_MONITORING_TYPE_NONE")
    None,

    @SerialName("OBS_MONITORING_TYPE_MONITOR_ONLY")
    MonitorOnly,

    @SerialName("OBS_MONITORING_TYPE_MONITOR_AND_OUTPUT")
    MonitorAndOutput,
}

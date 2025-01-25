package com.rejeq.ktobs.request.sceneitems

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object Alignment {
    const val CENTER = 0U
    const val LEFT = 1U
    const val RIGHT = 2U
    const val TOP = 4U
    const val BOTTOM = 8U
}

@Serializable
data class SceneItemTransform(
    @SerialName("sourceWidth") val sourceWidth: Float,
    @SerialName("sourceHeight") val sourceHeight: Float,
    @SerialName("positionX") val posX: Float,
    @SerialName("positionY") val posY: Float,
    @SerialName("rotation") val rotation: Float,
    @SerialName("scaleX") val scaleX: Float,
    @SerialName("scaleY") val scaleY: Float,
    @SerialName("width") val width: Float,
    @SerialName("height") val height: Float,
    @SerialName("alignment") val alignment: UInt,
    @SerialName("boundsType") val boundsType: BoundsType,
    @SerialName("boundsAlignment") val boundsAlignment: UInt,
    @SerialName("boundsWidth") val boundsWidth: Float,
    @SerialName("boundsHeight") val boundsHeight: Float,
    @SerialName("cropLeft") val cropLeft: UInt,
    @SerialName("cropRight") val cropRight: UInt,
    @SerialName("cropTop") val cropTop: UInt,
    @SerialName("cropBottom") val cropBottom: UInt,
    @SerialName("cropToBounds") val cropToBounds: Boolean? = null,
)

@Serializable
data class GetSceneItemTransformRequest(
    val sceneName: String?,
    val sceneUuid: String?,
    val sceneItemId: Int,
)

@Serializable
data class GetSceneItemTransformResponse(
    val sceneItemTransform: SceneItemTransform,
)

// Gets the transform and crop info of a scene item
// Scenes and Groups
suspend fun ObsSession.getSceneItemTransform(
    sceneName: String? = null,
    sceneUuid: String? = null,
    sceneItemId: Int,
): SceneItemTransform =
    callMethod<GetSceneItemTransformResponse, GetSceneItemTransformRequest>(
        "GetSceneItemTransform",
        GetSceneItemTransformRequest(sceneName, sceneUuid, sceneItemId),
    ).sceneItemTransform

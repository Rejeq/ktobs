package com.rejeq.ktobs.request.scenes

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SetSceneSceneTransitionOverrideRequest(
    val sceneName: String? = null,
    val sceneUuid: String? = null,
    val transitionName: String? = null,
    // Value are restricted from >= 50 and <= 20000
    val transitionDuration: Int? = null,
)

/**
 * Sets the scene transition overridden for a scene.
 *
 * @param sceneName Name of the scene
 * @param sceneUuid UUID of the scene
 * @param transitionName Name of the scene transition to use as override.
 *        Specify null to remove
 * @param transitionDuration Duration to use for any overridden transition.
 *        Specify null to remove
 */
suspend fun ObsSession.setSceneSceneTransitionOverride(
    sceneName: String? = null,
    sceneUuid: String? = null,
    transitionName: String? = null,
    transitionDuration: Int? = null,
) = callUnitMethod(
    "SetSceneSceneTransitionOverride",
    SetSceneSceneTransitionOverrideRequest(
        sceneName,
        sceneUuid,
        transitionName,
        transitionDuration,
    ),
)

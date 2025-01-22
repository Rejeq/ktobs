package com.rejeq.ktobs.event

import com.rejeq.ktobs.request.scene.Scene
import kotlinx.serialization.Serializable

/** A new scene has been created */
const val SceneCreatedEvent = "SceneCreated"

@Serializable
class SceneCreatedEventData(
    /** Name of the new scene */
    val sceneName: String,
    /** UUID of the new scene */
    val sceneUuid: String,
    /** Whether the new scene is a group */
    val isGroup: Boolean,
)

/** A scene has been removed */
const val SceneRemovedEvent = "SceneRemoved"

@Serializable
class SceneRemovedEventData(
    /** Name of the removed scene */
    val sceneName: String,
    /** UUID of the removed scene */
    val sceneUuid: String,
    /** Whether the scene was a group */
    val isGroup: Boolean,
)

const val SceneNameChangedEvent = "SceneNameChanged"

@Serializable
class SceneNameChangedEventData(
    /** UUID of the scene */
    val sceneUuid: String,
    /** Old name of the scene */
    val oldSceneName: String,
    /** New name of the scene */
    val sceneName: String,
)

const val CurrentProgramSceneChangedEvent = "CurrentProgramSceneChanged"

@Serializable
class CurrentProgramSceneChangedEventData(
    /** Name of the scene that was switched to */
    val sceneName: String,
    /** UUID of the scene that was switched to */
    val sceneUuid: String,
)

const val CurrentPreviewSceneChangedEvent = "CurrentPreviewSceneChanged"

@Serializable
class CurrentPreviewSceneChangedEventData(
    /** Name of the scene that was switched to */
    val sceneName: String,
    /** UUID of the scene that was switched to */
    val sceneUuid: String,
)

/** The list of scenes has changed */
const val SceneListChangedEvent = "SceneListChanged"

@Serializable
class SceneListChangedEventData(
    /** Updated array of scenes */
    val scenes: List<Scene>,
)

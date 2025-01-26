package com.rejeq.ktobs.event.config

import kotlinx.serialization.Serializable

/**
 * The current scene collection has begun changing.
 *
 * Note: We recommend using this event to trigger a pause of all polling
 *       requests, as performing any requests during a scene collection change
 *       is considered undefined behavior and can cause crashes!
 */
const val CurrentSceneCollectionChangingEvent = "CurrentSceneCollectionChanging"

@Serializable
class CurrentSceneCollectionChangingEventData(
    /** Name of the current scene collection */
    val sceneCollectionName: String,
)

/**
 * The current scene collection has changed.
 *
 * Note: If polling has been paused during CurrentSceneCollectionChanging, this
 *       is the que to restart polling.
 */
const val CurrentSceneCollectionChangedEvent = "CurrentSceneCollectionChanged"

@Serializable
class CurrentSceneCollectionChangedEventData(
    /** Name of the new scene collection */
    val sceneCollectionName: String,
)

/** The scene collection list has changed */
const val SceneCollectionListChangedEvent = "SceneCollectionListChanged"

@Serializable
class SceneCollectionListChangedEventData(
    /** Updated list of scene collections */
    val sceneCollections: List<String>,
)

/** The current profile has begun changing */
const val CurrentProfileChangingEvent = "CurrentProfileChanging"

@Serializable
class CurrentProfileChangingEventData(
    /** Name of the current profile */
    val profileName: String,
)

/** The current profile has changed */
const val CurrentProfileChangedEvent = "CurrentProfileChanged"

@Serializable
class CurrentProfileChangedEventData(
    /** Name of the new profile */
    val profileName: String,
)

/** The profile list has changed */
const val ProfileListChangedEvent = "ProfileListChanged"

@Serializable
class ProfileListChangedEventData(
    /** Updated list of profiles */
    val profiles: List<String>,
)

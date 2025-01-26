package com.rejeq.ktobs.event.transitions

import kotlinx.serialization.Serializable

/** The current scene transition has changed */
const val CurrentSceneTransitionChangedEvent = "CurrentSceneTransitionChanged"

@Serializable
class CurrentSceneTransitionChangedEventData(
    /** Name of the new transition */
    val transitionName: String,
    /** UUID of the new transition */
    val transitionUuid: String,
)

/** The current scene transition duration has changed */
const val CurrentSceneTransitionDurationChangedEvent =
    "CurrentSceneTransitionDurationChanged"

@Serializable
class CurrentSceneTransitionDurationChangedEventData(
    /** Transition duration in milliseconds */
    val transitionDuration: Int,
)

/** A scene transition has started */
const val SceneTransitionStartedEvent = "SceneTransitionStarted"

@Serializable
class SceneTransitionStartedEventData(
    /** Scene transition name */
    val transitionName: String,
    /** Scene transition UUID */
    val transitionUuid: String,
)

/**
 * A scene transition has completed fully
 *
 * Note: Does not appear to trigger when the transition is interrupted by the
 * user.
 */
const val SceneTransitionEndedEvent = "SceneTransitionEnded"

@Serializable
class SceneTransitionEndedEventData(
    /** Scene transition name */
    val transitionName: String,
    /** Scene transition UUID */
    val transitionUuid: String,
)

/**
 * A scene transition's video has completed fully.
 *
 * Useful for stinger transitions to tell when the video actually ends.
 * SceneTransitionEnded only signifies the cut point, not the completion of
 * transition playback.
 *
 * Note: Appears to be called by every transition, regardless of relevance.
 */
const val SceneTransitionVideoEndedEvent = "SceneTransitionVideoEnded"

@Serializable
class SceneTransitionVideoEndedEventData(
    /** Scene transition name */
    val transitionName: String,
    /** Scene transition UUID */
    val transitionUuid: String,
)

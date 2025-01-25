package com.rejeq.ktobs.event

import com.rejeq.ktobs.request.mediainputs.MediaAction
import kotlinx.serialization.Serializable

/** A media input has started playing */
const val MediaInputPlaybackStartedEvent = "MediaInputPlaybackStarted"

@Serializable
class MediaInputPlaybackStartedEventData(
    /** Name of the input */
    val inputName: String,
    /** UUID of the input */
    val inputUuid: String,
)

/** A media input has finished playing */
const val MediaInputPlaybackEndedEvent = "MediaInputPlaybackEnded"

@Serializable
class MediaInputPlaybackEndedEventData(
    /** Name of the input */
    val inputName: String,
    /** UUID of the input */
    val inputUuid: String,
)

/** An action has been performed on an input */
const val MediaInputActionTriggeredEvent = "MediaInputActionTriggered"

@Serializable
class MediaInputActionTriggeredEventData(
    /** Name of the input */
    val inputName: String,
    /** UUID of the input */
    val inputUuid: String,
    /** Action performed on the input */
    val mediaAction: MediaAction,
)

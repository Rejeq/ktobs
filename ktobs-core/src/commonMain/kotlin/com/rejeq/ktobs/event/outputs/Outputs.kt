package com.rejeq.ktobs.event.outputs

import kotlinx.serialization.Serializable

/** The state of the stream output has changed */
const val StreamStateChangedEvent = "StreamStateChanged"

@Serializable
class StreamStateChangedEventData(
    /** Whether the output is active */
    val outputActive: Boolean,
    /** The specific state of the output */
    val outputState: String,
)

/** The state of the record output has changed */
const val RecordStateChangedEvent = "RecordStateChanged"

@Serializable
class RecordStateChangedEventData(
    /** Whether the output is active */
    val outputActive: Boolean,
    /** The specific state of the output */
    val outputState: String,
    /** File name for the saved recording, if record stopped. null otherwise */
    val outputPath: String,
)

/**
 * The record output has started writing to a new file. For example, when a file
 * split happens
 */
const val RecordFileChangedEvent = "RecordFileChanged"

@Serializable
class RecordFileChangedEventData(
    /** File name that the output has begun writing to */
    val newOutputPath: String,
)

/** The state of the replay buffer output has changed */
const val ReplayBufferStateChangedEvent = "ReplayBufferStateChanged"

@Serializable
class ReplayBufferStateChangedEventData(
    /** Whether the output is active */
    val outputActive: Boolean,
    /** The specific state of the output */
    val outputState: String,
)

/** The state of the virtualcam output has changed */
const val VirtualcamStateChangedEvent = "VirtualcamStateChanged"

@Serializable
class VirtualcamStateChangedEventData(
    /** Whether the output is active */
    val outputActive: Boolean,
    /** The specific state of the output */
    val outputState: String,
)

/** The replay buffer has been saved */
const val ReplayBufferSavedEvent = "ReplayBufferSaved"

@Serializable
class ReplayBufferSavedEventData(
    /** Path of the saved replay file */
    val savedReplayPath: String,
)

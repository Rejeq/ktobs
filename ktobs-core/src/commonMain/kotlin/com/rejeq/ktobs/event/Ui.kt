package com.rejeq.ktobs.event

import kotlinx.serialization.Serializable

/** Studio mode has been enabled or disabled */
const val StudioModeStateChangedEvent = "StudioModeStateChanged"

@Serializable
class StudioModeStateChangedEventData(
    val studioModeEnabled: Boolean,
)

/**
 * A screenshot has been saved
 *
 * Note: Triggered for the screenshot feature available in
 * Settings -> Hotkeys -> Screenshot Output ONLY. Applications using
 * Get/SaveSourceScreenshot should implement a CustomEvent if this kind of
 * inter-client communication is desired.
 */
const val ScreenshotSavedEvent = "ScreenshotSaved"

@Serializable
class ScreenshotSavedEventData(
    /** Path of the saved image file */
    val savedScreenshotPath: String,
)

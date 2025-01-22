package com.rejeq.ktobs.request.sources

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
data class SaveSourceScreenshotRequest(
    val sourceName: String? = null,
    val sourceUuid: String? = null,
    val imageFormat: String,
    val imageFilePath: String,
    val imageWidth: Int? = null,
    val imageHeight: Int? = null,
    // -1 means "default", 0-100 for compression quality
    val imageCompressionQuality: Int? = null,
)

// Saves a screenshot of a source to the filesystem
// The imageWidth and imageHeight parameters are treated as "scale to inner",
// meaning the smallest ratio will be used and the aspect ratio of the original
// resolution is kept.
// Compatible with inputs and scenes
suspend fun ObsSession.saveSourceScreenshot(
    sourceName: String? = null,
    sourceUuid: String? = null,
    imageFormat: String,
    imageFilePath: String,
    imageWidth: Int? = null,
    imageHeight: Int? = null,
    imageCompressionQuality: Int? = null,
) = callUnitMethod(
    "SaveSourceScreenshot",
    SaveSourceScreenshotRequest(
        sourceName,
        sourceUuid,
        imageFormat,
        imageFilePath,
        imageWidth,
        imageHeight,
        imageCompressionQuality,
    ),
)

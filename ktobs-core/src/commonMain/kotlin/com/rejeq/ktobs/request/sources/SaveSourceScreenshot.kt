package com.rejeq.ktobs.request.sources

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callUnitMethod
import kotlinx.serialization.Serializable

@Serializable
class SaveSourceScreenshotRequest(
    val sourceName: String? = null,
    val sourceUuid: String? = null,
    val imageFormat: String,
    val imageFilePath: String,
    val imageWidth: Int? = null,
    val imageHeight: Int? = null,
    // -1 means "default", 0-100 for compression quality
    val imageCompressionQuality: Int? = null,
)

/**
 * Saves a screenshot of a source to the filesystem.
 *
 * The imageWidth and imageHeight parameters are treated as "scale to inner",
 * meaning the smallest ratio will be used and the aspect ratio of the original
 * resolution is kept.
 *
 * If imageWidth and imageHeight are not specified, the saved image will use the
 * full resolution of the source.
 *
 * @param sourceName Name of the source to take a screenshot of
 * @param sourceUuid UUID of the source to take a screenshot of
 * @param imageFormat Image compression format to use. Use GetVersion to get
 *        compatible image formats
 * @param imageFilePath Path to save the screenshot file to
 * @param imageWidth Width to scale the screenshot to
 * @param imageHeight Height to scale the screenshot to
 * @param imageCompressionQuality Compression quality to use.
 *        0 for high compression,
 *        100 for uncompressed.
 *        -1 to use "default" (whatever that means, idk)
 */
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

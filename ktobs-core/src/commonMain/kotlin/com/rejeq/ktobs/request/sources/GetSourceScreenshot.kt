package com.rejeq.ktobs.request.sources

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
class GetSourceScreenshotRequest(
    val sourceName: String? = null,
    val sourceUuid: String? = null,
    val imageFormat: String,
    val imageWidth: Int? = null,
    val imageHeight: Int? = null,
    // -1 means "default", 0-100 for compression quality
    val imageCompressionQuality: Int? = null,
)

/**
 * @property imageData Base64-encoded screenshot
 */
@Serializable
class GetSourceScreenshotResponse(
    val imageData: String,
)

/**
 * Gets a Base64-encoded screenshot of a source.
 *
 * The imageWidth and imageHeight parameters are treated as "scale to inner",
 * meaning the smallest ratio will be used and the aspect ratio of the original
 * resolution is kept.
 *
 * If imageWidth and imageHeight are not specified, the compressed image will
 * use the full resolution of the source.
 *
 * @param sourceName Name of the source to take a screenshot of
 * @param sourceUuid UUID of the source to take a screenshot of
 * @param imageFormat Image compression format to use. Use GetVersion to get
 *        compatible image formats
 * @param imageWidth Width to scale the screenshot to
 * @param imageHeight Height to scale the screenshot to
 * @param imageCompressionQuality Compression quality to use.
 *        0 for high compression,
 *        100 for uncompressed.
 *        -1 to use "default" (whatever that means, idk)
 */
suspend fun ObsSession.getSourceScreenshot(
    sourceName: String? = null,
    sourceUuid: String? = null,
    imageFormat: String,
    imageWidth: Int? = null,
    imageHeight: Int? = null,
    imageCompressionQuality: Int? = null,
): GetSourceScreenshotResponse =
    callMethod(
        "GetSourceScreenshot",
        GetSourceScreenshotRequest(
            sourceName,
            sourceUuid,
            imageFormat,
            imageWidth,
            imageHeight,
            imageCompressionQuality,
        ),
    )

package com.rejeq.ktobs.request.sources

import com.rejeq.ktobs.ObsSession
import com.rejeq.ktobs.callMethod
import kotlinx.serialization.Serializable

@Serializable
data class GetSourceScreenshotRequest(
    val sourceName: String? = null,
    val sourceUuid: String? = null,
    val imageFormat: String,
    val imageWidth: Int? = null,
    val imageHeight: Int? = null,
    // -1 means "default", 0-100 for compression quality
    val imageCompressionQuality: Int? = null,
)

@Serializable
data class GetSourceScreenshotResponse(
    val imageData: String,
)

// Gets a Base64-encoded screenshot of a source
// The imageWidth and imageHeight parameters are treated as "scale to inner",
// meaning the smallest ratio will be used and the aspect ratio of the original
// resolution is kept. Compatible with inputs and scenes
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

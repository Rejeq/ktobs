package com.rejeq.ktobs.model

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@JvmInline
@Serializable
value class SourceCapabilities(
    val value: Int = 0,
) {
    /**
     * Checks if this capability set contains the specified capabilities.
     *
     * @param subs The capability to check for
     * @return true if all specified capabilities are present
     */
    operator fun contains(subs: SourceCapabilities): Boolean =
        if (subs.value == 0) value == 0 else (value and subs.value) != 0

    /**
     * Combines this capability set with another.
     *
     * @param subs The capabilities to add
     * @return A new [SourceCapabilities] containing all capabilities from both
     *         sets
     */
    operator fun plus(subs: SourceCapabilities): SourceCapabilities =
        SourceCapabilities(value or subs.value)

    /**
     * Removes specified capability from this set
     *
     * @param subs The capabilities to remove
     * @return A new [SourceCapabilities] with the specified capabilities
     *         removed
     */
    operator fun minus(subs: SourceCapabilities): SourceCapabilities =
        SourceCapabilities(value and subs.value.inv())

    companion object {
        /** Source has video */
        val Video = SourceCapabilities(1 shl 0)

        /** Source has audio */
        val Audio = SourceCapabilities(1 shl 1)

        /** Async video flag */
        val Async = SourceCapabilities(1 shl 2)

        /** Source passes raw video data via RAM **/
        val AsyncVideo: SourceCapabilities = Async + Video

        /** Source uses custom drawing, rather than a default effect */
        val CustomDraw = SourceCapabilities(1 shl 3)

        /** Source supports interaction */
        val Interaction = SourceCapabilities(1 shl 5)

        /**
         * Source composites sub-sources
         *
         * When used specifies that the source composites one or more
         * sub-sources.
         * Sources that render sub-sources must implement the audio_render
         * callback in order to perform custom mixing of sub-sources.
         *
         * This capability flag is always set for transitions.
         */
        val Composite = SourceCapabilities(1 shl 6)

        /**
         * Source should not be fully duplicated
         *
         * When this is used, specifies that the source should not be fully
         * duplicated, and should prefer to duplicate via holding references
         * rather than full duplication.
         */
        val Duplicate = SourceCapabilities(1 shl 7)

        /**
         * Source is deprecated and should not be used
         */
        val Deprecated = SourceCapabilities(1 shl 8)

        /**
         * Source cannot have its audio monitored
         *
         * Specifies that this source may cause a feedback loop if audio is
         * monitored with a device selected as desktop audio.
         *
         * This is used primarily with desktop audio capture sources.
         */
        val DoNotSelfMonitor = SourceCapabilities(1 shl 9)

        /**
         * Source type is currently disabled and should not be shown to the user
         */
        val Disabled = SourceCapabilities(1 shl 10)

        /**
         * Source should enable monitoring by default.
         * Monitoring should be set by the frontend if this flag is set.
         */
        val MonitorByDefault = SourceCapabilities(1 shl 11)

        /** Used internally for audio submixing */
        val Submix = SourceCapabilities(1 shl 12)

        /**
         * Source type can be controlled by media controls
         */
        val ControllableMedia = SourceCapabilities(1 shl 13)

        /**
         * Source type provides cea708 data
         */
        val Cea708 = SourceCapabilities(1 shl 14)

        /**
         * Source understands SRGB rendering
         */
        val Srgb = SourceCapabilities(1 shl 15)

        /**
         * Source type prefers not to have its properties shown on creation
         * (prefers to rely on defaults first)
         */
        val DontShowProperties = SourceCapabilities(1 shl 16)

        /** Source requires a canvas to operate */
        val Canvas = SourceCapabilities(1 shl 17)
    }
}

package com.rejeq.ktobs.event.filters

import com.rejeq.ktobs.request.filters.Filter
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/** A source's filter list has been reindexed */
const val SourceFilterListReindexedEvent = "SourceFilterListReindexed"

@Serializable
class SourceFilterListReindexedEventData(
    /** Name of the source */
    val sourceName: String,
    /** Array of filter objects */
    val filters: List<Filter>,
)

/** A filter has been added to a source */
const val SourceFilterCreatedEvent = "SourceFilterCreated"

@Serializable
class SourceFilterCreatedEventData(
    /** Name of the source the filter was added to */
    val sourceName: String,
    /** Name of the filter */
    val filterName: String,
    /** The kind of the filter */
    val filterKind: String,
    /** Index position of the filter */
    val filterIndex: Int,
    /** The settings configured to the filter when it was created */
    val filterSettings: JsonElement,
    /** The default settings for the filter */
    val defaultFilterSettings: JsonElement,
)

/** A filter has been removed from a source */
const val SourceFilterRemovedEvent = "SourceFilterRemoved"

@Serializable
class SourceFilterRemovedEventData(
    /** Name of the source the filter was on */
    val sourceName: String,
    /** Name of the filter */
    val filterName: String,
)

/** The name of a source filter has changed */
const val SourceFilterNameChangedEvent = "SourceFilterNameChanged"

@Serializable
class SourceFilterNameChangedEventData(
    /** The source the filter is on */
    val sourceName: String,
    /** Old name of the filter */
    val oldFilterName: String,
    /** New name of the filter */
    val filterName: String,
)

/** A source filter's settings have changed (been updated) */
const val SourceFilterSettingsChangedEvent = "SourceFilterSettingsChanged"

@Serializable
class SourceFilterSettingsChangedEventData(
    /** Name of the source the filter is on */
    val sourceName: String,
    /** Name of the filter */
    val filterName: String,
    /** New settings object of the filter */
    val filterSettings: JsonElement,
)

/** A source filter's enable state has changed */
const val SourceFilterEnableStateChangedEvent = "SourceFilterEnableStateChanged"

@Serializable
class SourceFilterEnableStateChangedEventData(
    /** Name of the source the filter is on */
    val sourceName: String,
    /** Name of the filter */
    val filterName: String,
    /** Whether the filter is enabled */
    val filterEnabled: Boolean,
)

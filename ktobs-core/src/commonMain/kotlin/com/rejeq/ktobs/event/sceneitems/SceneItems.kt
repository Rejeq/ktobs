package com.rejeq.ktobs.event.sceneitems

import com.rejeq.ktobs.request.sceneitems.SceneItemTransform
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/** A scene item has been created. */
const val SceneItemCreatedEvent = "SceneItemCreated"

@Serializable
class SceneItemCreatedEventData(
    /** Name of the scene the item was added to */
    val sceneName: String,
    /** UUID of the scene the item was added to */
    val sceneUuid: String,
    /** Name of the underlying source (input/scene) */
    val sourceName: String,
    /** UUID of the underlying source (input/scene) */
    val sourceUuid: String,
    /** Numeric ID of the scene item */
    val sceneItemId: Long,
    /** Index position of the item */
    val sceneItemIndex: Int,
)

/**
 * A scene item has been removed.
 *
 * This event is not emitted when the scene the item is in is removed.
 */
const val SceneItemRemovedEvent = "SceneItemRemoved"

@Serializable
class SceneItemRemovedEventData(
    /** Name of the scene the item was removed from */
    val sceneName: String,
    /** UUID of the scene the item was removed from */
    val sceneUuid: String,
    /** Name of the underlying source (input/scene) */
    val sourceName: String,
    /** UUID of the underlying source (input/scene) */
    val sourceUuid: String,
    /** Numeric ID of the scene item */
    val sceneItemId: Long,
)

/** A scene's item list has been reindexed */
const val SceneItemListReindexedEvent = "SceneItemListReindexed"

@Serializable
class SceneItemListReindexedEventData(
    /** Name of the scene */
    val sceneName: String,
    /** UUID of the scene */
    val sceneUuid: String,
    /** Array of scene item objects */
    val sceneItems: List<JsonElement>,
)

/** A scene item's enable state has changed */
const val SceneItemEnableStateChangedEvent = "SceneItemEnableStateChanged"

@Serializable
class SceneItemEnableStateChangedEventData(
    /** Name of the scene the item is in */
    val sceneName: String,
    /** UUID of the scene the item is in */
    val sceneUuid: String,
    /** Numeric ID of the scene item */
    val sceneItemId: Long,
    /** Whether the scene item is enabled (visible) */
    val sceneItemEnabled: Boolean,
)

/** A scene item's lock state has changed */
const val SceneItemLockStateChangedEvent = "SceneItemLockStateChanged"

@Serializable
class SceneItemLockStateChangedEventData(
    /** Name of the scene the item is in */
    val sceneName: String,
    /** UUID of the scene the item is in */
    val sceneUuid: String,
    /** Numeric ID of the scene item */
    val sceneItemId: Long,
    /** Whether the scene item is locked */
    val sceneItemLocked: Boolean,
)

/** A scene item has been selected in the Ui */
const val SceneItemSelectedEvent = "SceneItemSelected"

@Serializable
class SceneItemSelectedEventData(
    /** Name of the scene the item is in */
    val sceneName: String,
    /** UUID of the scene the item is in */
    val sceneUuid: String,
    /** Numeric ID of the scene item */
    val sceneItemId: Long,
)

/** The transform/crop of a scene item has changed */
const val SceneItemTransformChangedEvent = "SceneItemTransformChanged"

@Serializable
class SceneItemTransformChangedEventData(
    /** The name of the scene the item is in */
    val sceneName: String,
    /** The UUID of the scene the item is in */
    val sceneUuid: String,
    /** Numeric ID of the scene item */
    val sceneItemId: Long,
    /** New transform/crop info of the scene item */
    val sceneItemTransform: SceneItemTransform,
)

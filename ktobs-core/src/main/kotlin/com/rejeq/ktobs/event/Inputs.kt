package com.rejeq.ktobs.event

import com.rejeq.ktobs.request.inputs.Input
import com.rejeq.ktobs.request.inputs.MonitorType
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

/** An input has been created */
const val InputCreatedEvent = "InputCreated"

@Serializable
class InputCreatedEventData(
    /** Name of the input */
    val inputName: String,
    /** UUID of the input */
    val inputUuid: String,
    /** The kind of the input */
    val inputKind: String,
    /** The unversioned kind of input (aka no _v2 stuff) */
    val unversionedInputKind: String,
    /** The settings configured to the input when it was created */
    val inputSettings: JsonElement,
    /** The default settings for the input */
    val defaultInputSettings: JsonElement,
)

/** An input has been removed */
const val InputRemovedEvent = "InputRemoved"

@Serializable
class InputRemovedEventData(
    /** Name of the input */
    val inputName: String,
    /** UUID of the input */
    val inputUuid: String,
)

/** The name of an input has changed */
const val InputNameChangedEvent = "InputNameChanged"

@Serializable
class InputNameChangedEventData(
    /** UUID of the input */
    val inputUuid: String,
    /** Old name of the input */
    val oldInputName: String,
    /** New name of the input */
    val inputName: String,
)

/**
 * An input's settings have changed (been updated).
 *
 * Note: On some inputs, changing values in the properties dialog will cause an
 * immediate update. Pressing the "Cancel" button will revert the settings,
 * resulting in another event being fired
 */
const val InputSettingsChangedEvent = "InputSettingsChanged"

@Serializable
class InputSettingsChangedEventData(
    /** Name of the input */
    val inputName: String,
    /** UUID of the input */
    val inputUuid: String,
    /** New settings object of the input */
    val inputSettings: JsonElement,
)

/**
 * An input's active state has changed.
 *
 * When an input is active, it means it's being shown by the program feed
 */
const val InputActiveStateChangedEvent = "InputActiveStateChanged"

@Serializable
class InputActiveStateChangedEventData(
    /** Name of the input */
    val inputName: String,
    /** UUID of the input */
    val inputUuid: String,
    /** Whether the input is active */
    val videoActive: Boolean,
)

/**
 * An input's show state has changed.
 *
 * When an input is showing, it means it's being shown by the preview or a
 * dialog
 */
const val InputShowStateChangedEvent = "InputShowStateChanged"

@Serializable
class InputShowStateChangedEventData(
    /** Name of the input */
    val inputName: String,
    /** UUID of the input */
    val inputUuid: String,
    /** Whether the input is showing */
    val videoShowing: Boolean,
)

/** An input's mute state has changed */
const val InputMuteStateChangedEvent = "InputMuteStateChanged"

@Serializable
class InputMuteStateChangedEventData(
    /** Name of the input */
    val inputName: String,
    /** UUID of the input */
    val inputUuid: String,
    /** Whether the input is muted */
    val inputMuted: Boolean,
)

/** An input's volume level has changed */
const val InputVolumeChangedEvent = "InputVolumeChanged"

@Serializable
class InputVolumeChangedEventData(
    /** Name of the input */
    val inputName: String,
    /** UUID of the input */
    val inputUuid: String,
    /** New volume level multiplier */
    val inputVolumeMul: Double,
    /** New volume level in dB */
    val inputVolumeDb: Double,
)

/** The audio balance value of an input has changed */
const val InputAudioBalanceChangedEvent = "InputAudioBalanceChanged"

@Serializable
class InputAudioBalanceChangedEventData(
    /** Name of the input */
    val inputName: String,
    /** UUID of the input */
    val inputUuid: String,
    /** New audio balance value of the input */
    val inputAudioBalance: Double,
)

/** The sync offset of an input has changed */
const val InputAudioSyncOffsetChangedEvent = "InputAudioSyncOffsetChanged"

@Serializable
class InputAudioSyncOffsetChangedEventData(
    /** Name of the input */
    val inputName: String,
    /** UUID of the input */
    val inputUuid: String,
    /** New sync offset in milliseconds */
    val inputAudioSyncOffset: Int,
)

/** The audio tracks of an input have changed */
const val InputAudioTracksChangedEvent = "InputAudioTracksChanged"

@Serializable
class InputAudioTracksChangedEventData(
    /** Name of the input */
    val inputName: String,
    /** UUID of the input */
    val inputUuid: String,
    /** Object of audio tracks along with their associated enable states */
    val inputAudioTracks: JsonElement,
)

/** The monitor type of input has changed */
const val InputAudioMonitorTypeChangedEvent = "InputAudioMonitorTypeChanged"

@Serializable
class InputAudioMonitorTypeChangedEventData(
    /** Name of the input */
    val inputName: String,
    /** UUID of the input */
    val inputUuid: String,
    /** New monitor type of the input */
    val monitorType: MonitorType,
)

/**
 * A high-volume event providing volume levels of all active inputs every 50
 * milliseconds
 */
const val InputVolumeMetersEvent = "InputVolumeMeters"

@Serializable
class InputVolumeMetersEventData(
    /** Array of active inputs with their associated volume levels */
    val inputs: List<Input>,
)

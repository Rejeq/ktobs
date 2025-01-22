package com.rejeq.ktobs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the status codes returned by OBS WebSocket requests.
 * These codes indicate the success, failure, or specific error conditions
 * of requests sent to the OBS WebSocket server.
 */
@Serializable
enum class RequestCode {
    /** Unknown status, should never be used. */
    @SerialName("0")
    Unknown,

    /** For internal use to signify a successful field check. */
    @SerialName("10")
    NoError,

    /** The request has succeeded. */
    @SerialName("100")
    Success,

    /** The `requestType` field is missing from the request data. */
    @SerialName("203")
    MissingRequestType,

    /** The request type is invalid or does not exist. */
    @SerialName("204")
    UnknownRequestType,

    /**
     * Generic error code.
     *
     * Note: A comment is required to be provided by obs-websocket.
     */
    @SerialName("205")
    GenericError,

    /** The request batch execution type is not supported. */
    @SerialName("206")
    UnsupportedRequestBatchExecutionType,

    /**
     * The server is not ready to handle the request.
     *
     * Note: This usually occurs during OBS scene collection change or exit.
     * Requests may be tried again after a delay if this code is given.
     */
    @SerialName("207")
    NotReady,

    /** A required request field is missing. */
    @SerialName("300")
    MissingRequestField,

    /** The request does not have a valid requestData object. */
    @SerialName("301")
    MissingRequestData,

    /**
     * Generic invalid request field message.
     *
     * Note: A comment is required to be provided by obs-websocket.
     */
    @SerialName("400")
    InvalidRequestField,

    /** A request field has the wrong data type. */
    @SerialName("401")
    InvalidRequestFieldType,

    /** A request field (number) is outside of the allowed range. */
    @SerialName("402")
    RequestFieldOutOfRange,

    /** A request field (string or array) is empty and cannot be. */
    @SerialName("403")
    RequestFieldEmpty,

    /**
     * There are too many request fields (eg. a request takes two optionals,
     * where only one is allowed at a time).
     */
    @SerialName("404")
    TooManyRequestFields,

    /** An output is running and cannot be in order to perform the request. */
    @SerialName("500")
    OutputRunning,

    /** An output is not running and should be. */
    @SerialName("501")
    OutputNotRunning,

    /** An output is paused and should not be. */
    @SerialName("502")
    OutputPaused,

    /** An output is not paused and should be. */
    @SerialName("503")
    OutputNotPaused,

    /** An output is disabled and should not be. */
    @SerialName("504")
    OutputDisabled,

    /** Studio mode is active and cannot be. */
    @SerialName("505")
    StudioModeActive,

    /** Studio mode is not active and should be. */
    @SerialName("506")
    StudioModeNotActive,

    /**
     * The resource was not found.
     *
     * Note: Resources are any kind of object in obs-websocket, like inputs,
     * profiles, outputs, etc.
     */
    @SerialName("600")
    ResourceNotFound,

    /** The resource already exists. */
    @SerialName("601")
    ResourceAlreadyExists,

    /** The type of resource found is invalid. */
    @SerialName("602")
    InvalidResourceType,

    /**
     * There are not enough instances of the resource in order to perform the
     * request.
     */
    @SerialName("603")
    NotEnoughResources,

    /**
     * The state of the resource is invalid. For example, if the resource is
     * blocked from being accessed.
     */
    @SerialName("604")
    InvalidResourceState,

    /** The specified input (obs_source_t-OBS_SOURCE_TYPE_INPUT) had the wrong
     * kind.
     */
    @SerialName("605")
    InvalidInputKind,

    /**
     * The resource does not support being configured.
     * This is particularly relevant to transitions, where they do not always
     * have changeable settings.
     */
    @SerialName("606")
    ResourceNotConfigurable,

    /**
     * The specified filter (obs_source_t-OBS_SOURCE_TYPE_FILTER) had the
     * wrong kind.
     */
    @SerialName("607")
    InvalidFilterKind,

    /** Creating the resource failed. */
    @SerialName("700")
    ResourceCreationFailed,

    /** Performing an action on the resource failed. */
    @SerialName("701")
    ResourceActionFailed,

    /**
     * Processing the request failed unexpectedly.
     *
     * Note: A comment is required to be provided by obs-websocket.
     */
    @SerialName("702")
    RequestProcessingFailed,

    /**
     * The combination of request fields cannot be used to perform an action.
     */
    @SerialName("703")
    CannotAct,
}

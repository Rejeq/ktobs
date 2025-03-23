package com.rejeq.ktobs

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient

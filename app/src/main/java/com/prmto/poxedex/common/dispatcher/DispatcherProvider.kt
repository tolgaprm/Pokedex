package com.prmto.poxedex.common.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val IO: CoroutineDispatcher
    val Main: CoroutineDispatcher
    val Default: CoroutineDispatcher
}
package com.prmto.poxedex.common.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher

class TestDispatcher : DispatcherProvider {
    override val IO: CoroutineDispatcher
        get() = StandardTestDispatcher()
    override val Main: CoroutineDispatcher
        get() = StandardTestDispatcher()
    override val Default: CoroutineDispatcher
        get() = StandardTestDispatcher()
}
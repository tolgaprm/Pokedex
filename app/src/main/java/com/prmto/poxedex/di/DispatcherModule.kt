package com.prmto.poxedex.di

import com.prmto.poxedex.common.dispatcher.DefaultDispatcher
import com.prmto.poxedex.common.dispatcher.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DispatcherModule {

    @Binds
    @ViewModelScoped
    abstract fun bindDispatcher(
        defaultDispatcher: DefaultDispatcher
    ): DispatcherProvider
}
package com.prmto.poxedex.di

import com.prmto.poxedex.data.remote.datasource.PokemonRemoteDataSource
import com.prmto.poxedex.data.remote.datasource.PokemonRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class SourceModule {

    @Binds
    @ViewModelScoped
    abstract fun bindPokemonRemoteDataSource(
        pokemonRemoteDataSourceImpl: PokemonRemoteDataSourceImpl
    ): PokemonRemoteDataSource
}
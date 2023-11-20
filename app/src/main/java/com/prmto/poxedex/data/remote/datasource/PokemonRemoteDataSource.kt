package com.prmto.poxedex.data.remote.datasource

import com.prmto.poxedex.common.NetworkResponse
import com.prmto.poxedex.data.dto.AllPokemonResponse
import com.prmto.poxedex.data.dto.PokemonDetailDto

interface PokemonRemoteDataSource {
    suspend fun getAllPokemons(
        page: Int,
    ): NetworkResponse<AllPokemonResponse>

    suspend fun searchPokemonsByName(
        name: String
    ): NetworkResponse<PokemonDetailDto>

    suspend fun searchPokemonsById(
        id: Int
    ): NetworkResponse<PokemonDetailDto>
}
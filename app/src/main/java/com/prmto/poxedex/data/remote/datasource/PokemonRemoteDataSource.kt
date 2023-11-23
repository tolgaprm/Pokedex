package com.prmto.poxedex.data.remote.datasource

import com.prmto.poxedex.common.NetworkResponse
import com.prmto.poxedex.data.dto.AllPokemonResponse
import com.prmto.poxedex.data.dto.PokemonDetailDto
import com.prmto.poxedex.data.dto.PokemonSpeciesDto

interface PokemonRemoteDataSource {
    suspend fun getAllPokemons(
        page: Int,
    ): NetworkResponse<AllPokemonResponse>

    suspend fun getPokemonDetail(
        path: String
    ): NetworkResponse<PokemonDetailDto>

    suspend fun getPokemonSpecies(
        pokemonId: String
    ): NetworkResponse<PokemonSpeciesDto>
}
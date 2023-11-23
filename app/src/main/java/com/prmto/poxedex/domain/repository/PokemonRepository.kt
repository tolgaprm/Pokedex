package com.prmto.poxedex.domain.repository

import com.prmto.poxedex.common.NetworkResponse
import com.prmto.poxedex.domain.model.PokemonDetail
import com.prmto.poxedex.domain.model.PokemonSpecies
import com.prmto.poxedex.domain.model.SinglePokemon

interface PokemonRepository {
    suspend fun getAllPokemons(
        page: Int,
    ): NetworkResponse<List<SinglePokemon>>

    suspend fun getPokemonDetail(
        path: String
    ): NetworkResponse<PokemonDetail>

    suspend fun getPokemonSpecies(
        pokemonId: String
    ): NetworkResponse<PokemonSpecies>
}
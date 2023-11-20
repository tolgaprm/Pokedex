package com.prmto.poxedex.domain.repository

import com.prmto.poxedex.common.NetworkResponse
import com.prmto.poxedex.domain.model.PokemonDetail
import com.prmto.poxedex.domain.model.SinglePokemon

interface PokemonRepository {
    suspend fun getAllPokemons(
        page: Int,
    ): NetworkResponse<List<SinglePokemon>>

    suspend fun searchPokemons(
        query: String
    ): NetworkResponse<PokemonDetail>
}
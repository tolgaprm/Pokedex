package com.prmto.poxedex.domain.usecase

import com.prmto.poxedex.common.NetworkResponse
import com.prmto.poxedex.common.mapResponse
import com.prmto.poxedex.data.mapper.toSinglePokemon
import com.prmto.poxedex.domain.model.SinglePokemon
import com.prmto.poxedex.domain.repository.PokemonRepository
import javax.inject.Inject

class SearchPokemonUseCase @Inject constructor(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(
        query: String
    ): NetworkResponse<SinglePokemon> {
        val response = repository.searchPokemons(query = query)

        return response.mapResponse { it.toSinglePokemon() }
    }
}
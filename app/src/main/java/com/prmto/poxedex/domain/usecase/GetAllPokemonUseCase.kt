package com.prmto.poxedex.domain.usecase

import com.prmto.poxedex.common.mapResponse
import com.prmto.poxedex.domain.repository.PokemonRepository
import javax.inject.Inject

class GetAllPokemonUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(
        page: Int
    ) = pokemonRepository.getAllPokemons(page).mapResponse {
        it.map { singlePokemon ->
            singlePokemon.copy(
                name = singlePokemon.name.replaceFirstChar { it.uppercase() }
            )
        }
    }
}
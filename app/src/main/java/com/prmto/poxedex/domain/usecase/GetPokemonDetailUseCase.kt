package com.prmto.poxedex.domain.usecase

import com.prmto.poxedex.common.NetworkResponse
import com.prmto.poxedex.domain.model.PokemonDetail
import com.prmto.poxedex.domain.repository.PokemonRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend operator fun invoke(pokemonId: String): NetworkResponse<PokemonDetail> {
        return coroutineScope {
            val pokemonDetail =
                async { pokemonRepository.getPokemonDetail(path = pokemonId) }.await()
            val pokemonSpecies =
                async { pokemonRepository.getPokemonSpecies(pokemonId = pokemonId) }.await()

            if (pokemonDetail is NetworkResponse.Success && pokemonSpecies is NetworkResponse.Success) {
                NetworkResponse.Success(
                    pokemonDetail.data.copy(
                        species = pokemonSpecies.data,
                        weight = pokemonDetail.data.weight / 10,
                        height = pokemonDetail.data.height / 10,
                    )
                )
            } else {
                NetworkResponse.Error("Error getting pokemon detail")
            }
        }
    }
}
package com.prmto.poxedex.data.remote.datasource

import com.prmto.poxedex.common.NetworkResponse
import com.prmto.poxedex.common.TestConstants
import com.prmto.poxedex.data.dto.AllPokemonResponse
import com.prmto.poxedex.data.dto.PokemonDetailDto
import com.prmto.poxedex.data.dto.PokemonSpeciesDto
import com.prmto.poxedex.data.util.createNetworkResponseByStatus
import kotlinx.coroutines.delay

class FakePokemonRemoteDataSource : PokemonRemoteDataSource {
    var isReturnError = false
    override suspend fun getAllPokemons(page: Int): NetworkResponse<AllPokemonResponse> {
        delay(NETWORK_DELAY)
        return createNetworkResponseByStatus(isReturnError = isReturnError) {
            val allPokemonResponse = when (page) {
                0 -> TestConstants.ALL_POKEMON_RESPONSE_PAGE_0

                1 -> TestConstants.ALL_POKEMON_RESPONSE_PAGE_1

                else -> TestConstants.ALL_POKEMON_RESPONSE_PAGE_1
            }
            allPokemonResponse
        }
    }

    override suspend fun getPokemonDetail(path: String): NetworkResponse<PokemonDetailDto> {
        delay(NETWORK_DELAY)
        return createNetworkResponseByStatus(isReturnError = isReturnError) {
            val pokemonDetail = if (path.toIntOrNull() != null) {
                TestConstants.POKEMON_DETAILS_RESPONSE.firstOrNull { it.id == path.toInt() }
            } else {
                TestConstants.POKEMON_DETAILS_RESPONSE.firstOrNull { it.name == path }
            }

            pokemonDetail
        }
    }

    override suspend fun getPokemonSpecies(pokemonId: String): NetworkResponse<PokemonSpeciesDto> {
        delay(NETWORK_DELAY)
        return createNetworkResponseByStatus(isReturnError = isReturnError) {
            TestConstants.POKEMON_SPECIES_RESPONSE.find { it.id == pokemonId.toInt() }
        }
    }

    companion object {
        const val NETWORK_DELAY = 1000L
    }
}

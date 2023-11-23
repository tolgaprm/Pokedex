package com.prmto.poxedex.data.remote.datasource

import com.prmto.poxedex.common.Constants
import com.prmto.poxedex.common.NetworkResponse
import com.prmto.poxedex.data.dto.AllPokemonResponse
import com.prmto.poxedex.data.dto.PokemonDetailDto
import com.prmto.poxedex.data.dto.PokemonSpeciesDto
import com.prmto.poxedex.data.remote.api.PokeApi
import com.prmto.poxedex.data.safeApiCallReturnNetworkResponse
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(
    private val pokeApi: PokeApi
) : PokemonRemoteDataSource {
    override suspend fun getAllPokemons(
        page: Int
    ): NetworkResponse<AllPokemonResponse> {
        return safeApiCallReturnNetworkResponse {
            pokeApi.getAllPokemons(
                limit = Constants.PAGE_SIZE,
                offset = if (page == 0) 0 else (page * Constants.PAGE_SIZE)
            )
        }
    }

    override suspend fun getPokemonDetail(path: String): NetworkResponse<PokemonDetailDto> {
        return safeApiCallReturnNetworkResponse { pokeApi.getPokemonDetail(path = path) }
    }

    override suspend fun getPokemonSpecies(pokemonId: String): NetworkResponse<PokemonSpeciesDto> {
        return safeApiCallReturnNetworkResponse { pokeApi.getPokemonSpecies(pokemonId = pokemonId) }
    }
}
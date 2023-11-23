package com.prmto.poxedex.data.repository

import com.prmto.poxedex.common.NetworkResponse
import com.prmto.poxedex.common.mapResponse
import com.prmto.poxedex.data.mapper.toPokemonDetail
import com.prmto.poxedex.data.mapper.toPokemonSpecies
import com.prmto.poxedex.data.remote.datasource.PokemonRemoteDataSource
import com.prmto.poxedex.domain.model.PokemonDetail
import com.prmto.poxedex.domain.model.PokemonSpecies
import com.prmto.poxedex.domain.model.SinglePokemon
import com.prmto.poxedex.domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {
    override suspend fun getAllPokemons(
        page: Int,
    ): NetworkResponse<List<SinglePokemon>> {
        return pokemonRemoteDataSource.getAllPokemons(
            page = page
        ).mapResponse { it.results }
    }

    override suspend fun getPokemonDetail(path: String): NetworkResponse<PokemonDetail> {
        return pokemonRemoteDataSource.getPokemonDetail(path = path)
            .mapResponse { it.toPokemonDetail() }
    }

    override suspend fun getPokemonSpecies(pokemonId: String): NetworkResponse<PokemonSpecies> {
        return pokemonRemoteDataSource.getPokemonSpecies(pokemonId = pokemonId).mapResponse {
            it.toPokemonSpecies()
        }
    }
}
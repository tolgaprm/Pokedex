package com.prmto.poxedex.data.remote.api

import com.prmto.poxedex.common.PokeApiPaths
import com.prmto.poxedex.data.dto.AllPokemonResponse
import com.prmto.poxedex.data.dto.PokemonDetailDto
import com.prmto.poxedex.data.dto.PokemonSpeciesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
    @GET(PokeApiPaths.POKEMON)
    suspend fun getAllPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<AllPokemonResponse>

    @GET("${PokeApiPaths.POKEMON}/{pokemon_name_or_id}")
    suspend fun getPokemonDetail(
        @Path("pokemon_name_or_id") path: String
    ): Response<PokemonDetailDto>

    @GET("${PokeApiPaths.POKEMON_SPECIES}/{pokemon_id}")
    suspend fun getPokemonSpecies(
        @Path("pokemon_id") pokemonId: String
    ): Response<PokemonSpeciesDto>
}
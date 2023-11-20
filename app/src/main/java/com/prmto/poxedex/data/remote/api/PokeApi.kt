package com.prmto.poxedex.data.remote.api

import com.prmto.poxedex.common.PokeApiPaths
import com.prmto.poxedex.data.dto.AllPokemonResponse
import com.prmto.poxedex.data.dto.PokemonDetailDto
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

    @GET("${PokeApiPaths.POKEMON}/{pokemon_name}")
    suspend fun searchPokemonsByName(
        @Path("pokemon_name") name: String
    ): Response<PokemonDetailDto>

    @GET("${PokeApiPaths.POKEMON}/{pokemon_id}")
    suspend fun searchPokemonsById(
        @Path("pokemon_id") id: Int
    ): Response<PokemonDetailDto>
}
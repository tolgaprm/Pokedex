package com.prmto.poxedex.data.remote.datasource

import com.prmto.poxedex.common.NetworkResponse
import com.prmto.poxedex.data.dto.AllPokemonResponse
import com.prmto.poxedex.data.dto.PokemonDetailDto
import com.prmto.poxedex.data_generator.abilityDto
import com.prmto.poxedex.data_generator.pokemonDetailDto
import com.prmto.poxedex.data_generator.singlePokemon
import com.prmto.poxedex.data_generator.statDto
import com.prmto.poxedex.data_generator.typeDto
import kotlinx.coroutines.delay

class FakePokemonRemoteDataSource : PokemonRemoteDataSource {
    var isReturnError = false
    override suspend fun getAllPokemons(page: Int): NetworkResponse<AllPokemonResponse> {
        delay(NETWORK_DELAY)
        return if (isReturnError) {
            NetworkResponse.Error("Error")
        } else {
            val allPokemonResponse = when (page) {
                0 -> allPokemonResponsePage0

                1 -> allPokemonResponsePage1

                else -> allPokemonResponsePage1
            }
            NetworkResponse.Success(allPokemonResponse)
        }
    }

    override suspend fun searchPokemons(query: String): NetworkResponse<PokemonDetailDto> {
        delay(NETWORK_DELAY)
        return if (isReturnError) {
            NetworkResponse.Error("Error")
        } else {
            val pokemonDetail = if (query.toIntOrNull() != null) {
                pokemonDetailsDto.firstOrNull { it.id == query.toInt() }
            } else {
                pokemonDetailsDto.firstOrNull { it.name == query }
            }

            pokemonDetail?.let {
                NetworkResponse.Success(pokemonDetail)
            } ?: kotlin.run { NetworkResponse.Error("Error") }
        }
    }

    companion object {
        const val NETWORK_DELAY = 1000L
    }
}

private val allPokemonResponsePage0 = AllPokemonResponse(
    count = 3,
    next = "testpage1",
    previous = null,
    results = listOf(
        singlePokemon(name = "ıvysaur", id = 2),
        singlePokemon(name = "balbasaur", id = 1),
        singlePokemon(name = "venusaur", id = 3),
    )
)

private val allPokemonResponsePage1 = AllPokemonResponse(
    count = 3,
    next = "testpage2",
    previous = null,
    results = listOf(
        singlePokemon(name = "spearow", id = 21),
        singlePokemon(name = "fearow", id = 22),
        singlePokemon(name = "ekans", id = 23),
    )
)

private val pokemonDetailsDto = listOf(
    pokemonDetailDto(
        id = 1,
        name = "bulbasaur",
        types = listOf(typeDto("grass"), typeDto("poison")),
        stats = listOf(
            statDto(45, "hp"),
            statDto(49, "attack"),
            statDto(49, "defense"),
            statDto(65, "special-attack"),
            statDto(65, "special-defense"),
            statDto(45, "speed")
        ),
        abilities = listOf(
            abilityDto("overgrow"),
            abilityDto("chlorophyll")
        )
    ),
    pokemonDetailDto(
        id = 5,
        name = "charmeleon",
        types = listOf(typeDto("fire")),
        stats = listOf(
            statDto(58, "hp"),
            statDto(64, "attack"),
            statDto(58, "defense"),
            statDto(80, "special-attack"),
            statDto(65, "special-defense"),
            statDto(80, "speed")
        ),
        abilities = listOf(
            abilityDto("blaze"),
            abilityDto("solar-power")
        )
    ),
    pokemonDetailDto(
        id = 25,
        name = "pikachu",
        types = listOf(typeDto("electric")),
        stats = listOf(
            statDto(35, "hp"),
            statDto(55, "attack"),
            statDto(40, "defense"),
            statDto(50, "special-attack"),
            statDto(50, "special-defense"),
            statDto(90, "speed")
        ),
        abilities = listOf(
            abilityDto("static"),
            abilityDto("lightning-rod")
        )
    )
)
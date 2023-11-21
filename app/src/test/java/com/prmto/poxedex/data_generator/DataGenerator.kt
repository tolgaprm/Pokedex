package com.prmto.poxedex.data_generator

import com.prmto.poxedex.common.Constants
import com.prmto.poxedex.common.PokeApiPaths
import com.prmto.poxedex.data.dto.AbilityDto
import com.prmto.poxedex.data.dto.PokemonDetailDto
import com.prmto.poxedex.data.dto.SpeciesDto
import com.prmto.poxedex.data.dto.StatDto
import com.prmto.poxedex.data.dto.TypeDto
import com.prmto.poxedex.domain.model.SinglePokemon

fun singlePokemon(
    name: String,
    id: Int
): SinglePokemon {
    return SinglePokemon(
        name = name,
        url = "${Constants.POKE_API_BASE_URL}${PokeApiPaths.POKEMON}/$id/"
    )
}

fun pokemonDetailDto(
    id: Int,
    name: String,
    types: List<TypeDto>,
    stats: List<StatDto>,
    abilities: List<AbilityDto>
): PokemonDetailDto {
    return PokemonDetailDto(
        id = id,
        name = name,
        types = types,
        height = 7,
        weight = 69,
        stats = stats,
        abilities = abilities
    )
}

fun typeDto(
    typeName: String
): TypeDto {
    return TypeDto(
        slot = 1,
        type = SpeciesDto(typeName, "type/$typeName")
    )
}

fun statDto(
    baseStat: Int,
    statName: String
): StatDto {
    return StatDto(
        baseStat = baseStat,
        effort = 0,
        stat = SpeciesDto(
            name = statName,
            url = "stat/$statName"
        )
    )
}

fun abilityDto(
    abilityName: String
): AbilityDto {
    return AbilityDto(
        ability = SpeciesDto(
            name = abilityName,
            url = "ability/$abilityName"
        ),
        isHidden = false,
        slot = 1
    )
}
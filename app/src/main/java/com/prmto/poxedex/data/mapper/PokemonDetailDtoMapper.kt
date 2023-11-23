package com.prmto.poxedex.data.mapper

import com.prmto.poxedex.data.dto.PokemonDetailDto
import com.prmto.poxedex.data.dto.TypeDto
import com.prmto.poxedex.domain.model.PokemonDetail
import com.prmto.poxedex.domain.model.PokemonTypeWithColors
import com.prmto.poxedex.domain.model.StatType

fun PokemonDetailDto.toPokemonDetail(): PokemonDetail {
    return PokemonDetail(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        pokemonTypeWithColors = types.map { it.toPokemonTypeWithColors() },
        height = height.toFloat(),
        weight = weight.toFloat(),
        stats = stats.associate {
            StatType.fromValue(it.stat.name.lowercase()) to it.baseStat
        },
        abilitiesWithSeparatedWithComma = abilities.take(2)
            .joinToString(",") { it.ability.name.replaceFirstChar { it.uppercase() } }
    )
}

fun TypeDto.toPokemonTypeWithColors(): PokemonTypeWithColors {
    return PokemonTypeWithColors.valueOf(type.name.uppercase())
}
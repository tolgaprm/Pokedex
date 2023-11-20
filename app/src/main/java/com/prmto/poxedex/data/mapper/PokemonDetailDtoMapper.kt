package com.prmto.poxedex.data.mapper

import com.prmto.poxedex.data.dto.PokemonDetailDto
import com.prmto.poxedex.data.dto.StatDto
import com.prmto.poxedex.data.dto.TypeDto
import com.prmto.poxedex.domain.model.PokemonDetail
import com.prmto.poxedex.domain.model.Stat
import com.prmto.poxedex.domain.model.Type

fun PokemonDetailDto.toPokemonDetail(): PokemonDetail {
    return PokemonDetail(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
        types = types.map { it.toType() },
        height = height,
        weight = weight,
        stats = stats.map { it.toStat() }
    )
}

fun TypeDto.toType(): Type {
    return Type(
        slot = slot,
        name = type.name
    )
}

fun StatDto.toStat(): Stat {
    return Stat(
        baseStat = baseStat,
        name = stat.name
    )
}
package com.prmto.poxedex.data.mapper

import com.prmto.poxedex.data.dto.PokemonSpeciesDto
import com.prmto.poxedex.domain.model.PokemonSpecies

fun PokemonSpeciesDto.toPokemonSpecies(): PokemonSpecies {
    return PokemonSpecies(
        id = id,
        name = name,
        flavorText = flavorTextEntries.toFlavorText()
    )
}
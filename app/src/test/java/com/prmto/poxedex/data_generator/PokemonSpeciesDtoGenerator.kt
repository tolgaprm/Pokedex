package com.prmto.poxedex.data_generator

import com.prmto.poxedex.data.dto.FlavorTextEntryDto
import com.prmto.poxedex.data.dto.LanguageDto
import com.prmto.poxedex.data.dto.PokemonSpeciesDto
import com.prmto.poxedex.data.dto.VersionDto

fun pokemonSpeciesDto(
    id: Int,
    name: String,
    flavorTexts: List<FlavorTextEntryDto>
): PokemonSpeciesDto {
    return PokemonSpeciesDto(
        baseHappiness = 50,
        id = id,
        name = name,
        flavorTextEntries = flavorTexts
    )
}

fun flavorTextEntryDto(
    flavorText: String,
    language: LanguageDto
): FlavorTextEntryDto {
    return FlavorTextEntryDto(
        flavorText = flavorText,
        language = language,
        version = VersionDto(name = "red", "version1")
    )
}

fun languageDto(
    name: String
): LanguageDto {
    return LanguageDto(
        name = name,
        url = "$name test url"
    )
}
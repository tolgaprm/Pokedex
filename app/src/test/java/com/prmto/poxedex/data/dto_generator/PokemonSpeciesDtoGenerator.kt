package com.prmto.poxedex.data.dto_generator

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
    language: LanguageDto,
    version: Int = 10
): FlavorTextEntryDto {
    return FlavorTextEntryDto(
        flavorText = flavorText,
        language = language,
        version = versionDto(version = version)
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

fun versionDto(
    version: Int
): VersionDto {
    return VersionDto(
        name = "version",
        url = "https://pokeapi.co/api/v2/version/$version/"
    )
}
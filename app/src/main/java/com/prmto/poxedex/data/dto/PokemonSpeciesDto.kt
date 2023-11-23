package com.prmto.poxedex.data.dto

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesDto(
    @SerializedName("base_happiness") val baseHappiness: Int,
    val id: Int,
    val name: String,
    @SerializedName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntryDto>
)

data class FlavorTextEntryDto(
    @SerializedName("flavor_text") val flavorText: String,
    val language: LanguageDto,
    val version: VersionDto
)

data class LanguageDto(
    val name: String,
    val url: String
)

data class VersionDto(
    val name: String,
    val url: String
)
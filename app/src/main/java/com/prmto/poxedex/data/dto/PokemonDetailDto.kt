package com.prmto.poxedex.data.dto

import com.google.gson.annotations.SerializedName

data class PokemonDetailDto(
    val id: Int,
    val name: String,
    val types: List<TypeDto>,
    val height: Int,
    val weight: Int,
    val stats: List<StatDto>,
    val abilities: List<AbilityDto>
)

data class TypeDto(
    val slot: Int,
    val type: SpeciesDto
)

data class SpeciesDto(
    val name: String,
    val url: String
)

data class StatDto(
    @SerializedName("base_stat") val baseStat: Int,
    val effort: Int,
    val stat: SpeciesDto
)


data class AbilityDto(
    val ability: SpeciesDto,
    @SerializedName("is_hidden") val isHidden: Boolean,
    val slot: Int
)

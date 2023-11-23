package com.prmto.poxedex.domain.model

import androidx.annotation.ColorRes
import com.prmto.poxedex.domain.util.PokemonUtilFunctions

data class PokemonDetail(
    val id: Int,
    val name: String,
    val pokemonTypeWithColors: List<PokemonTypeWithColors>,
    val height: Float,
    val weight: Float,
    val stats: Map<StatType, Int>,
    val species: PokemonSpecies? = null,
    val abilitiesWithSeparatedWithComma: String
) {
    @ColorRes
    val pokemonColorRes = pokemonTypeWithColors.first().color

    val imageUrl get() = PokemonUtilFunctions.getImageUrl(id = id)
}

enum class StatType(val value: String) {
    HP("hp"),
    ATTACK("attack"),
    DEFENSE("defense"),
    SPECIAL_ATTACK("special-attack"),
    SPECIAL_DEFENSE("special-defense"),
    SPEED("speed");

    companion object {
        fun fromValue(value: String) = values().first { it.value == value }
    }
}
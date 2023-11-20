package com.prmto.poxedex.domain.model

import com.prmto.poxedex.presentation.util.PokemonTypeWithColors

data class PokemonDetail(
    val id: Int,
    val name: String,
    val types: List<Type>,
    val height: Int,
    val weight: Int,
    val stats: List<Stat>,
) {
    val pokemonTypeWithColors: List<PokemonTypeWithColors>
        get() = types.map { PokemonTypeWithColors.valueOf(it.name.uppercase()) }
}

data class Stat(
    val baseStat: Int,
    val name: String
)

data class Type(
    val slot: Int,
    val name: String
)
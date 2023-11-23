package com.prmto.poxedex.domain.model

import com.prmto.poxedex.domain.util.PokemonUtilFunctions

data class SinglePokemon(
    val name: String,
    val url: String
) {
    val imageUrl: String
        get() = PokemonUtilFunctions.getImageUrl(id = id)


    val id: Int
        get() = PokemonUtilFunctions.getIdFromUrl(url = url)
}
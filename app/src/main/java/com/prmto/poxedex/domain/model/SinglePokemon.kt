package com.prmto.poxedex.domain.model

import com.prmto.poxedex.common.Constants

data class SinglePokemon(
    val name: String,
    val url: String
) {
    fun getImageUrl(): String {
        return "${Constants.POKEMON_IMAGE_URL}$id.png"
    }

    val id: Int
        get() {
            val index = url.split("/".toRegex()).dropLast(1).last()
            return index.toInt()
        }
}
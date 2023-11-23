package com.prmto.poxedex.domain.util

import com.prmto.poxedex.common.Constants

object PokemonUtilFunctions {
    fun getImageUrl(id: Int): String {
        return "${Constants.POKEMON_IMAGE_URL}$id.png"
    }

    fun getIdFromUrl(url: String): Int {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return index.toInt()
    }
}
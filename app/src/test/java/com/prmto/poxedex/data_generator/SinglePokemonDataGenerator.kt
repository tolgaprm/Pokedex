package com.prmto.poxedex.data_generator

import com.prmto.poxedex.common.Constants
import com.prmto.poxedex.common.PokeApiPaths
import com.prmto.poxedex.domain.model.SinglePokemon

fun singlePokemon(
    name: String,
    id: Int
): SinglePokemon {
    return SinglePokemon(
        name = name,
        url = "${Constants.POKE_API_BASE_URL}${PokeApiPaths.POKEMON}/$id/"
    )
}
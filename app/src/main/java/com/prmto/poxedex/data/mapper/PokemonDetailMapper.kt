package com.prmto.poxedex.data.mapper

import com.prmto.poxedex.common.Constants
import com.prmto.poxedex.common.PokeApiPaths
import com.prmto.poxedex.domain.model.PokemonDetail
import com.prmto.poxedex.domain.model.SinglePokemon

fun PokemonDetail.toSinglePokemon() = SinglePokemon(
    name = name,
    url = "${Constants.POKE_API_BASE_URL}${PokeApiPaths.POKEMON}/$id/",
)
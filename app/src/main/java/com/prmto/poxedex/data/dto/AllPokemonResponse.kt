package com.prmto.poxedex.data.dto

import com.prmto.poxedex.domain.model.SinglePokemon

data class AllPokemonResponse(
    val count: Int,
    val next: String,
    val previous: String?,
    val results: List<SinglePokemon>
)
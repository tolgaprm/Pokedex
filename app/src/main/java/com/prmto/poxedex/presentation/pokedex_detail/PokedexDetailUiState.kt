package com.prmto.poxedex.presentation.pokedex_detail

import androidx.annotation.StringRes
import com.prmto.poxedex.domain.model.PokemonDetail

data class PokedexDetailUiState(
    val isLoading: Boolean = false,
    val pokemonDetail: PokemonDetail? = null,
    @StringRes val errorMessageResId: Int? = null,
    val isChevronBackButtonVisible: Boolean = false,
    val pokemonId: String = ""
)
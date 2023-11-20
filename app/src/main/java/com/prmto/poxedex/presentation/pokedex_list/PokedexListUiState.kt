package com.prmto.poxedex.presentation.pokedex_list

import androidx.annotation.StringRes
import com.prmto.poxedex.domain.model.SinglePokemon

data class PokedexListUiState(
    val isLoading: Boolean = false,
    val pokemons: List<SinglePokemon> = emptyList(),
    @StringRes val errorMessageRes: Int? = null,
    val isSearchActive: Boolean = false,
    val searchQuery: String = "",
    val sortType: SortType = SortType.NUMBER,
    val isLastPage: Boolean = false
)

enum class SortType {
    NAME, NUMBER
}
package com.prmto.poxedex.presentation.pokedex_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prmto.poxedex.R
import com.prmto.poxedex.common.NetworkResponse
import com.prmto.poxedex.common.dispatcher.DispatcherProvider
import com.prmto.poxedex.domain.model.SinglePokemon
import com.prmto.poxedex.domain.usecase.GetAllPokemonUseCase
import com.prmto.poxedex.domain.usecase.SearchPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexListViewModel @Inject constructor(
    private val getAllPokemonUseCase: GetAllPokemonUseCase,
    private val searchPokemonUseCase: SearchPokemonUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private var currentPage = 0
    private val allPokemons = MutableStateFlow<List<SinglePokemon>>(emptyList())
    private var pokemonsBySearch = MutableStateFlow<List<SinglePokemon>>(emptyList())
    private var searchJob: Job? = null

    private val _uiState = MutableStateFlow(PokedexListUiState())
    val uiState: StateFlow<PokedexListUiState> = _uiState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _uiState.update { it.copy(isLoading = false) }
    }

    init {
        fetchPokemons()
    }

    private fun fetchPokemons() {
        if (uiState.value.isSearchActive) return
        viewModelScope.launch(dispatcherProvider.IO + coroutineExceptionHandler) {
            _uiState.update { it.copy(isLoading = true) }
            val response = getAllPokemonUseCase(
                page = currentPage
            )
            when (response) {
                is NetworkResponse.Success -> {
                    allPokemons.update { it + response.data }
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessageRes = null,
                            pokemons = allPokemons.value,
                            isLastPage = response.data.isEmpty()
                        )
                    }
                }

                is NetworkResponse.Error -> {
                    updateErrorState()
                }
            }
        }
    }

    fun setQuery(query: String) {
        val isSearchActive = query.isNotBlank()
        _uiState.update {
            it.copy(
                searchQuery = query,
                isSearchActive = isSearchActive,
                errorMessageRes = null
            )
        }

        if (isSearchActive) {
            searchPokemons()
        } else {
            updateStateNotSearchActiveState()
        }
    }

    private fun searchPokemons() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch(dispatcherProvider.IO + coroutineExceptionHandler) {
            delay(300) // Add some delay because of the user can type fast, so we don't want to make a request for each character
            _uiState.update { it.copy(isLoading = true) }
            val response = searchPokemonUseCase(
                query = uiState.value.searchQuery
            )
            when (response) {
                is NetworkResponse.Success -> {
                    pokemonsBySearch.update { listOf(response.data) }
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessageRes = null,
                            pokemons = pokemonsBySearch.value
                        )
                    }
                }

                is NetworkResponse.Error -> {
                    if (pokemonsBySearch.value.isEmpty()) {
                        updateErrorState(R.string.no_found_pokemon)
                    } else {
                        updateErrorState()
                    }
                }
            }
        }
    }

    fun tryAgainToFetchPokemons() {
        _uiState.update { it.copy(pokemons = emptyList()) }
        if (uiState.value.isSearchActive) {
            searchPokemons()
        } else {
            fetchPokemons()
        }
    }

    private fun updateErrorState(
        errorMessageRes: Int = R.string.error_message
    ) {
        _uiState.update {
            it.copy(isLoading = false, errorMessageRes = errorMessageRes)
        }
    }

    private fun updateStateNotSearchActiveState() {
        _uiState.update {
            it.copy(pokemons = allPokemons.value, isLoading = false)
        }
        pokemonsBySearch.update { emptyList() }
    }

    fun setSortType(sortType: SortType) {
        _uiState.update { it.copy(sortType = sortType) }
        sortPokemonsBySortType(pokemons = _uiState.value.pokemons)
    }

    private fun sortPokemonsBySortType(pokemons: List<SinglePokemon>) {
        viewModelScope.launch(dispatcherProvider.Default + coroutineExceptionHandler) {
            val sortedPokemonList = when (uiState.value.sortType) {
                SortType.NAME -> pokemons.sortedBy { it.name }
                SortType.NUMBER -> pokemons.sortedBy { it.id }
            }
            _uiState.update { it.copy(pokemons = sortedPokemonList) }
        }
    }

    fun fetchNextPage() {
        currentPage++
        fetchPokemons()
    }
}

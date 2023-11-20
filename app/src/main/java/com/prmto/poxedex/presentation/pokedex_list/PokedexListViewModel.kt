package com.prmto.poxedex.presentation.pokedex_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prmto.poxedex.R
import com.prmto.poxedex.common.NetworkResponse
import com.prmto.poxedex.common.dispatcher.DispatcherProvider
import com.prmto.poxedex.domain.usecase.GetAllPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexListViewModel @Inject constructor(
    private val getAllPokemonUseCase: GetAllPokemonUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private var currentPage = 0

    private val _uiState = MutableStateFlow(PokedexListUiState())
    val uiState: StateFlow<PokedexListUiState> = _uiState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.update { it.copy(isLoading = false) }
        Log.e("PokedexListViewModel", "Error: ${throwable.message}")
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
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessageRes = null,
                            pokemons = it.pokemons + response.data
                        )
                    }
                }

                is NetworkResponse.Error -> {
                    updateErrorState()
                }
            }
        }
    }

    fun tryAgainToFetchPokemons() {
        fetchPokemons()
    }

    private fun updateErrorState(
        errorMessageRes: Int = R.string.error_message
    ) {
        _uiState.update {
            it.copy(
                isLoading = false,
                errorMessageRes = errorMessageRes
            )
        }
    }

    fun fetchNextPage() {
        currentPage++
        fetchPokemons()
    }
}
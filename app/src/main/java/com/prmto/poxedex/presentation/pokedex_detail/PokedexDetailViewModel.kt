package com.prmto.poxedex.presentation.pokedex_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prmto.poxedex.R
import com.prmto.poxedex.common.NavArgs
import com.prmto.poxedex.common.NetworkResponse
import com.prmto.poxedex.common.dispatcher.DispatcherProvider
import com.prmto.poxedex.domain.usecase.GetPokemonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _uiState = MutableStateFlow(PokedexDetailUiState())
    val uiState: StateFlow<PokedexDetailUiState> = _uiState.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.update {
            it.copy(
                isLoading = false,
                errorMessageResId = R.string.error_message
            )
        }
    }

    init {
        val pokemonId = PokedexDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).pokedexId
        updatePokemonDetails(newPokemonId = pokemonId.toInt())
    }

    private fun getPokemonDetail(
        pokemonId: String
    ) {
        viewModelScope.launch(dispatcherProvider.IO + exceptionHandler) {
            _uiState.update { it.copy(isLoading = true) }
            when (val response = getPokemonDetailUseCase(pokemonId = pokemonId)) {
                is NetworkResponse.Success -> {
                    _uiState.update {
                        it.copy(
                            pokemonDetail = response.data,
                            isLoading = false,
                            errorMessageResId = null
                        )
                    }
                }

                is NetworkResponse.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessageResId = R.string.error_message
                        )
                    }
                }
            }
        }
    }

    fun retry() {
        _uiState.update { it.copy(errorMessageResId = null) }
        getPokemonDetail(pokemonId = uiState.value.currentPokemonId.toString())
    }

    private fun updatePokemonDetails(newPokemonId: Int) {
        _uiState.update {
            it.copy(
                currentPokemonId = newPokemonId,
                isChevronBackButtonVisible = newPokemonId != 1
            )
        }
        getPokemonDetail(pokemonId = newPokemonId.toString())
    }

    fun onPreviousPokemonClick() {
        val previousPokemonId = uiState.value.currentPokemonId - 1
        updatePokemonDetails(newPokemonId = previousPokemonId)
        setNewPokemonDetailIdToSavedStateHandle(newPokemonId = previousPokemonId)
    }

    fun onNextPokemonClick() {
        val nextPokemonId = uiState.value.currentPokemonId + 1
        updatePokemonDetails(newPokemonId = nextPokemonId)
        setNewPokemonDetailIdToSavedStateHandle(newPokemonId = nextPokemonId)
    }

    private fun setNewPokemonDetailIdToSavedStateHandle(newPokemonId: Int) {
        savedStateHandle[NavArgs.POKEDEX_ID] = newPokemonId.toString()
    }
}
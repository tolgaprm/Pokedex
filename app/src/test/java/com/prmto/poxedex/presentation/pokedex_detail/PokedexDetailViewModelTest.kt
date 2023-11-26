package com.prmto.poxedex.presentation.pokedex_detail

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.TurbineTestContext
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.prmto.poxedex.R
import com.prmto.poxedex.common.Constants
import com.prmto.poxedex.common.NavArgs
import com.prmto.poxedex.common.dispatcher.TestDispatcher
import com.prmto.poxedex.data.remote.datasource.FakePokemonRemoteDataSource
import com.prmto.poxedex.data.repository.PokemonRepositoryImpl
import com.prmto.poxedex.domain.model.PokemonDetail
import com.prmto.poxedex.domain.model.PokemonSpecies
import com.prmto.poxedex.domain.model.PokemonTypeWithColors
import com.prmto.poxedex.domain.model_generator.statType
import com.prmto.poxedex.domain.usecase.GetPokemonDetailUseCase
import com.prmto.poxedex.rules.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PokedexDetailViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: PokedexDetailViewModel
    private lateinit var remoteDataSource: FakePokemonRemoteDataSource
    private lateinit var savedStateHandle: SavedStateHandle

    @Before
    fun setUp() {
        remoteDataSource = FakePokemonRemoteDataSource()
        savedStateHandle = SavedStateHandle(mapOf(NavArgs.POKEDEX_ID to "25"))
        val pokemonRepository = PokemonRepositoryImpl(pokemonRemoteDataSource = remoteDataSource)
        val getPokemonDetailUseCase = GetPokemonDetailUseCase(pokemonRepository = pokemonRepository)
        val dispatcherProvider = TestDispatcher()
        viewModel = PokedexDetailViewModel(
            savedStateHandle = savedStateHandle,
            dispatcherProvider = dispatcherProvider,
            getPokemonDetailUseCase = getPokemonDetailUseCase
        )
    }

    @Test
    fun whenViewModelInit_getPokemonDetail_returnSuccess_updateStateProperly() = runTest {
        val expectedPokemonDetail = PokemonDetail(
            id = 25,
            name = "Pikachu",
            pokemonTypeWithColors = listOf(PokemonTypeWithColors.ELECTRIC),
            height = 0.4f,
            weight = 6.0f,
            stats = statType(
                hpValue = 35,
                attackValue = 55,
                defenseValue = 40,
                specialAttackValue = 50,
                specialDefenseValue = 50,
                speedValue = 90
            ),
            species = PokemonSpecies(
                id = 25,
                name = "pikachu",
                flavorText = "pikachu flavor1 en"
            ),
            abilitiesWithSeparatedWithComma = "Static,Lightning-rod"
        )
        viewModel.uiState.test {
            // In getPokemonDetailFunction
            val emissionOne = awaitItem()
            assertThat(emissionOne.currentPokemonId).isEqualTo(25)
            assertThat(emissionOne.isChevronBackButtonVisible).isTrue()
            assertThat(emissionOne.isLoading).isTrue()
            advanceUntilIdle()

            // In getPokemonDetailFunction response success block
            val emissionTwo = awaitItem()
            assertThat(emissionTwo.isLoading).isFalse()
            assertThat(emissionTwo.errorMessageResId).isNull()
            assertThat(emissionTwo.pokemonDetail).isEqualTo(expectedPokemonDetail)
            assertImageUrl(emissionTwo.pokemonDetail)
        }
    }

    @Test
    fun whenViewModelInit_getPokemonDetail_returnError_updateStateProperly() = runTest {
        remoteDataSource.isReturnError = true
        viewModel.uiState.test {
            // In getPokemonDetailFunction
            awaitItem()
            advanceUntilIdle()

            // In getPokemonDetailFunction response success block
            val emissionTwo = awaitItem()
            assertThat(emissionTwo.isLoading).isFalse()
            assertThat(emissionTwo.errorMessageResId).isEqualTo(R.string.error_message)
        }
    }

    @Test
    fun test_onPreviousPokemonClick_updateProperly() = runTest {
        viewModel.uiState.test {
            completeProcessInInit()
            viewModel.onPreviousPokemonClick()
            val emission = awaitItem()
            assertThat(emission.currentPokemonId).isEqualTo(24)
            assertThat(PokedexDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).pokedexId).isEqualTo(
                "24"
            )
        }
    }

    @Test
    fun test_onNextPokemonClick_updateProperly() = runTest {
        viewModel.uiState.test {
            completeProcessInInit()
            viewModel.onNextPokemonClick()
            val emission = awaitItem()
            assertThat(emission.currentPokemonId).isEqualTo(26)
            assertThat(PokedexDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).pokedexId).isEqualTo(
                "26"
            )
        }
    }

    @Test
    fun test_retry_updateProperly() = runTest {
        remoteDataSource.isReturnError = true
        viewModel.uiState.test {
            completeProcessInInit()
            viewModel.retry()
            val emission = awaitItem()
            assertThat(emission.errorMessageResId).isNull()
        }
    }

    private fun assertImageUrl(pokemonDetail: PokemonDetail?) {
        assertThat(pokemonDetail?.imageUrl)
            .isEqualTo(Constants.POKEMON_IMAGE_URL + pokemonDetail?.id + ".png")
    }

    private fun TurbineTestContext<PokedexDetailUiState>.completeProcessInInit() = runTest {
        awaitItem()
        advanceUntilIdle()
        awaitItem()
    }
}
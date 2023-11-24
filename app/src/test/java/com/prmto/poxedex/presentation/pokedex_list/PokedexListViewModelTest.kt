package com.prmto.poxedex.presentation.pokedex_list

import app.cash.turbine.TurbineTestContext
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.prmto.poxedex.R
import com.prmto.poxedex.common.Constants
import com.prmto.poxedex.common.dispatcher.TestDispatcher
import com.prmto.poxedex.data.remote.datasource.FakePokemonRemoteDataSource
import com.prmto.poxedex.data.repository.PokemonRepositoryImpl
import com.prmto.poxedex.domain.model.SinglePokemon
import com.prmto.poxedex.domain.model_generator.singlePokemon
import com.prmto.poxedex.domain.usecase.GetAllPokemonUseCase
import com.prmto.poxedex.domain.usecase.SearchPokemonUseCase
import com.prmto.poxedex.rules.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PokedexListViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: PokedexListViewModel
    private lateinit var fakePokemonRemoteDataSource: FakePokemonRemoteDataSource

    @Before
    fun setup() {
        fakePokemonRemoteDataSource = FakePokemonRemoteDataSource()
        val pokemonRepository = PokemonRepositoryImpl(fakePokemonRemoteDataSource)
        val getAllPokemonUseCase = GetAllPokemonUseCase(pokemonRepository)
        val searchPokemonUseCase = SearchPokemonUseCase(pokemonRepository)
        val testDispatcher = TestDispatcher()
        viewModel = PokedexListViewModel(
            getAllPokemonUseCase = getAllPokemonUseCase,
            searchPokemonUseCase = searchPokemonUseCase,
            dispatcherProvider = testDispatcher
        )
    }

    @Test
    fun whenViewModelInit_fetchAllPokemons_returnSuccess_updateStateProperly() = runTest {
        // Current page is 0, The expected pokemons are on the page 0
        val expectedPokemons = expectedPokemonsForPage0
        viewModel.uiState.test {
            val emissionOne = awaitItem()
            assertThat(emissionOne.isLoading).isTrue()
            advanceUntilIdle()

            val emissionTwo = awaitItem()
            assertFetchPokemonsOnSuccess(uiState = emissionTwo, expectedPokemons = expectedPokemons)
        }
    }

    @Test
    fun whenViewModelInit_fetchAllPokemons_returnError_updateStateProperly() = runTest {
        fakePokemonRemoteDataSource.isReturnError = true
        viewModel.uiState.test {
            val emissionOne = awaitItem()
            assertThat(emissionOne.isLoading).isTrue()
            advanceUntilIdle()

            val emissionTwo = awaitItem()
            assertThat(emissionTwo.isLoading).isFalse()
            assertThat(emissionTwo.errorMessageRes).isEqualTo(R.string.error_message)
            assertThat(emissionTwo.pokemons).isEmpty()
        }
    }

    @Test
    fun whenFetchNextPage_returnSuccess_updateStateProperly() = runTest {
        // The expected pokemons are sumOf page 0 and page 1 pokemons
        val expectedPokemons =
            expectedPokemonsForPage0 + expectedPokemonsForPage1

        viewModel.uiState.test {
            completeFetchPokemonsForPage0()
            viewModel.fetchNextPage() // The current page is increase 1 so the next page is 1

            val emission = awaitItem()
            assertThat(emission.isLoading).isTrue()
            advanceUntilIdle()

            val emissionTwo = awaitItem()
            assertFetchPokemonsOnSuccess(uiState = emissionTwo, expectedPokemons = expectedPokemons)
        }
    }

    @Test
    fun test_setSortType_sortsPokemonsByName() = runTest {
        val sortType = SortType.NAME
        val expectedSortedPokemons = listOf(
            singlePokemon(name = "Balbasaur", id = 1),
            singlePokemon(name = "Ivysaur", id = 2),
            singlePokemon(name = "Venusaur", id = 3),
        )
        viewModel.uiState.test {
            completeFetchPokemonsForPage0()
            viewModel.setSortType(sortType)

            val emission = awaitItem()
            assertThat(emission.sortType).isEqualTo(sortType)

            //In sortPokemonsBySortType
            val emission2 = awaitItem()
            assertThat(emission2.pokemons).isEqualTo(expectedSortedPokemons)
        }
    }

    @Test
    fun test_setQuery_whenQueryIsNotBlank_searchPokemons_returnSuccess_updateStateProperly() =
        runTest {
            val query = "pikachu"
            val expectedSearchedPokemons = listOf(
                singlePokemon(name = "Pikachu", id = 25)
            )

            viewModel.uiState.test {
                completeFetchPokemonsForPage0()
                viewModel.setQuery(query = query)

                val emissionOne = awaitItem()
                assertThat(emissionOne.searchQuery).isEqualTo(query)
                assertThat(emissionOne.isSearchActive).isTrue()

                // In searchPokemons function
                val emissionTwo = awaitItem()
                assertThat(emissionTwo.isLoading).isTrue()
                advanceUntilIdle()

                // Success
                val emissionThree = awaitItem()
                assertFetchPokemonsOnSuccess(
                    uiState = emissionThree,
                    expectedPokemons = expectedSearchedPokemons
                )
            }
        }

    @Test
    fun test_setQuery_whenQueryIsNotBlank_searchPokemons_notFound_updateStateProperly() =
        runTest {
            val query = "notContains"

            viewModel.uiState.test {
                completeFetchPokemonsForPage0()
                viewModel.setQuery(query = query)

                val emissionOne = awaitItem()
                assertThat(emissionOne.searchQuery).isEqualTo(query)
                assertThat(emissionOne.isSearchActive).isTrue()

                // In searchPokemons function
                val emissionTwo = awaitItem()
                assertThat(emissionTwo.isLoading).isTrue()
                advanceUntilIdle()

                // Error
                val emissionThree = awaitItem()
                assertThat(emissionThree.isLoading).isFalse()
                assertThat(emissionThree.errorMessageRes).isEqualTo(R.string.no_found_pokemon)
            }
        }

    @Test
    fun test_setQuery_whenQueryIsBlank_updateStateNotSearchActiveState() =
        runTest {
            val query = " "

            viewModel.uiState.test {
                completeFetchPokemonsForPage0()
                viewModel.setQuery(query = query)

                val emissionOne = awaitItem()
                assertThat(emissionOne.searchQuery).isEqualTo(query)
                assertThat(emissionOne.isSearchActive).isFalse()
            }
        }

    private fun assertFetchPokemonsOnSuccess(
        uiState: PokedexListUiState, expectedPokemons: List<SinglePokemon>
    ) {
        assertThat(uiState.isLoading).isFalse()
        assertThat(uiState.errorMessageRes).isNull()
        assertThat(uiState.pokemons).isEqualTo(expectedPokemons)
        assertPokemonsImages(uiState.pokemons)
    }

    private fun assertPokemonsImages(pokemons: List<SinglePokemon>) {
        pokemons.forEach {
            assertThat(it.imageUrl).isEqualTo(Constants.POKEMON_IMAGE_URL + it.id + ".png")
        }
    }

    private fun TurbineTestContext<PokedexListUiState>.completeFetchPokemonsForPage0() = runTest {
        awaitItem() // This is the first emission
        advanceUntilIdle() // Wait to complete coroutines
        awaitItem()  // Completed the fetch pokemons for page 0
    }

    private val expectedPokemonsForPage0 = listOf(
        singlePokemon(name = "Ivysaur", id = 2),
        singlePokemon(name = "Balbasaur", id = 1),
        singlePokemon(name = "Venusaur", id = 3),
    )

    private val expectedPokemonsForPage1 = listOf(
        singlePokemon(name = "Spearow", id = 21),
        singlePokemon(name = "Fearow", id = 22),
        singlePokemon(name = "Ekans", id = 23),
    )
}
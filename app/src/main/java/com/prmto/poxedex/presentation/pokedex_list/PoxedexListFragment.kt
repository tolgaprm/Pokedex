package com.prmto.poxedex.presentation.pokedex_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.prmto.poxedex.databinding.FragmentPoxedexListBinding
import com.prmto.poxedex.presentation.pokedex_list.adapter.PokemonListAdapter
import com.prmto.poxedex.presentation.pokedex_list.sort_dialog.SortDialogFragment
import com.prmto.poxedex.presentation.util.GridLayoutItemDecoration
import com.prmto.poxedex.presentation.util.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PoxedexListFragment : Fragment() {
    private var _binding: FragmentPoxedexListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PokedexListViewModel by viewModels()

    private lateinit var pokemonListAdapter: PokemonListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPoxedexListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        setupRecyclerView()
        collectUiState()
        setupClickListeners()
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { uiState ->
                    binding.errorMessage = uiState.errorMessageRes?.let { getString(it) }
                    binding.pokedexListUiState = uiState
                    pokemonListAdapter.submitList(uiState.pokemons)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        pokemonListAdapter = PokemonListAdapter()
        val spanCount = (binding.rvPoxedexList.layoutManager as GridLayoutManager).spanCount
        binding.rvPoxedexList.adapter = pokemonListAdapter
        binding.rvPoxedexList.addItemDecoration(
            GridLayoutItemDecoration(spanCount = spanCount)
        )
        addPaginationScrollListener()
    }

    private fun addPaginationScrollListener() {
        binding.rvPoxedexList.addOnScrollListener(object :
            PaginationScrollListener(binding.rvPoxedexList.layoutManager as GridLayoutManager) {
            override fun loadMoreItems() {
                viewModel.fetchNextPage()
            }

            override fun isLastPage(): Boolean {
                return viewModel.uiState.value.isLastPage
            }

            override fun isLoading(): Boolean {
                return viewModel.uiState.value.isLoading
            }
        })
    }

    private fun setupClickListeners() {
        binding.includeErrorView.btnErrorTryAgain.setOnClickListener {
            viewModel.tryAgainToFetchPokemons()
        }

        binding.imbSort.setOnClickListener {
            SortDialogFragment().show(
                childFragmentManager,
                SortDialogFragment.TAG
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.prmto.poxedex.presentation.pokedex_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.prmto.poxedex.R
import com.prmto.poxedex.databinding.FragmentPoxedexDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokedexDetailFragment : Fragment() {
    private var _binding: FragmentPoxedexDetailBinding? = null
    private val binding get() = _binding!!

    private var pokemonChipTypeCreator: PokemonChipTypeCreator? = null
    private val viewModel: PokedexDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPoxedexDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonChipTypeCreator = PokemonChipTypeCreator(context = requireContext())
        binding.viewModel = this.viewModel
        setupClickListeners()
        collectUiState()
    }

    private fun setupClickListeners() {
        binding.imbNavigateUp.setOnClickListener {
            setStatusColor(
                requireContext().resources.getColor(
                    R.color.red,
                    requireActivity().theme
                )
            )
            findNavController().navigateUp()
        }

        binding.includeErrorView.btnErrorTryAgain.setOnClickListener {
            viewModel.retry()
        }
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collectLatest { uiState ->
                    binding.errorMessage = uiState.errorMessageResId?.let { getString(it) }
                    binding.pokedexDetailUiState = uiState
                    uiState.pokemonDetail?.let { pokemonDetail ->
                        setStatusBarAndPokemonColors(pokemonDetail.pokemonColorRes)
                        if (pokemonDetail.pokemonTypeWithColors.isNotEmpty()) {
                            pokemonChipTypeCreator?.createPokemonChips(
                                chipGroup = binding.typeChipGroup,
                                pokemonTypeWithColors = pokemonDetail.pokemonTypeWithColors
                            )
                        }
                    }
                }
            }
        }
    }

    private fun setStatusBarAndPokemonColors(colorInt: Int) {
        val colorResId = requireContext().resources.getColor(
            colorInt,
            null
        )

        binding.featureStatsWidget.setAllProgressTints(colorResId)
        binding.featureStatsWidget.setAllTitleColors(colorResId)
        binding.flPokedexDetail.setBackgroundColor(colorResId)
        binding.tvAboutTitle.setTextColor(colorResId)
        binding.tvBaseStatsTitle.setTextColor(colorResId)
        setStatusColor(colorResId)
    }

    private fun setStatusColor(colorResId: Int) {
        requireActivity().window.statusBarColor = colorResId
    }

    override fun onDestroyView() {
        super.onDestroyView()
        pokemonChipTypeCreator = null
        _binding = null
    }
}
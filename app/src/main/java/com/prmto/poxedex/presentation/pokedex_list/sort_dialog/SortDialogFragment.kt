package com.prmto.poxedex.presentation.pokedex_list.sort_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.prmto.poxedex.databinding.SortDialogFragmentBinding
import com.prmto.poxedex.presentation.pokedex_list.PokedexListViewModel

class SortDialogFragment : DialogFragment() {

    private var _binding: SortDialogFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PokedexListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SortDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireParentFragment())[PokedexListViewModel::class.java]
        binding.viewModel = viewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "SortDialog"
    }
}
package com.prmto.poxedex.presentation.pokedex_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prmto.poxedex.databinding.FragmentPoxedexDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PoxedexDetailFragment : Fragment() {
    private var _binding: FragmentPoxedexDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPoxedexDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.prmto.poxedex.presentation.pokedex_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.prmto.poxedex.databinding.PokeItemBinding
import com.prmto.poxedex.domain.model.SinglePokemon

class PokemonListAdapter :
    ListAdapter<SinglePokemon, PokemonViewHolder>(PokemonDiffer()) {

    private var onClickPokedex: (String) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder.create(
            binding = PokeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onClickPokedex = onClickPokedex
        )
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemonItem = getItem(position)
        holder.bind(pokemon = pokemonItem)
    }

    fun setOnClickListener(onClickPokedex: (pokedexId: String) -> Unit) {
        this.onClickPokedex = onClickPokedex
    }
}
package com.prmto.poxedex.presentation.pokedex_list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.prmto.poxedex.databinding.PokeItemBinding
import com.prmto.poxedex.domain.model.SinglePokemon

class PokemonViewHolder(
    private val binding: PokeItemBinding,
    private val onClickPokedex: (String) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        pokemon: SinglePokemon
    ) {
        binding.pokemon = pokemon
        binding.root.setOnClickListener {
            onClickPokedex(pokemon.id.toString())
        }
        binding.executePendingBindings()
    }

    companion object {
        fun create(
            binding: PokeItemBinding,
            onClickPokedex: (String) -> Unit
        ): PokemonViewHolder {
            return PokemonViewHolder(binding, onClickPokedex)
        }
    }
}
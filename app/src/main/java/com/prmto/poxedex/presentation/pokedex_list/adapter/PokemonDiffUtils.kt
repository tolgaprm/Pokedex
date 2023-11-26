package com.prmto.poxedex.presentation.pokedex_list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.prmto.poxedex.domain.model.SinglePokemon

class PokemonDiffer : DiffUtil.ItemCallback<SinglePokemon>() {
    override fun areItemsTheSame(oldItem: SinglePokemon, newItem: SinglePokemon): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SinglePokemon, newItem: SinglePokemon): Boolean {
        return oldItem == newItem
    }
}
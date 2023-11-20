package com.prmto.poxedex.presentation.pokedex_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.prmto.poxedex.databinding.PokeItemBinding
import com.prmto.poxedex.domain.model.SinglePokemon

class PokemonListAdapter :
    ListAdapter<SinglePokemon, PokemonListAdapter.PokemonViewHolder>(PokemonDiffer()) {

    class PokemonViewHolder(private val binding: PokeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(
                parent: ViewGroup
            ): PokemonViewHolder {
                val binding = PokeItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return PokemonViewHolder(binding)
            }
        }

        fun bind(
            pokemon: SinglePokemon
        ) {
            binding.pokemon = pokemon
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder.create(parent = parent)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemonItem = getItem(position)
        holder.bind(pokemon = pokemonItem)
    }
}

class PokemonDiffer : DiffUtil.ItemCallback<SinglePokemon>() {
    override fun areItemsTheSame(oldItem: SinglePokemon, newItem: SinglePokemon): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: SinglePokemon, newItem: SinglePokemon): Boolean {
        return oldItem == newItem
    }
}
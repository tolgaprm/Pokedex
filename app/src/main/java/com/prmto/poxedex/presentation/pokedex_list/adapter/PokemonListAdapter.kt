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

    private var onClickPokedex: (String) -> Unit = { }

    inner class PokemonViewHolder(private val binding: PokeItemBinding) :
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
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = PokeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemonItem = getItem(position)
        holder.bind(pokemon = pokemonItem)
    }

    fun setOnClickListener(onClickPokedex: (pokedexId: String) -> Unit) {
        this.onClickPokedex = onClickPokedex
    }
}

class PokemonDiffer : DiffUtil.ItemCallback<SinglePokemon>() {
    override fun areItemsTheSame(oldItem: SinglePokemon, newItem: SinglePokemon): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SinglePokemon, newItem: SinglePokemon): Boolean {
        return oldItem == newItem
    }
}
package com.prmto.poxedex.presentation.pokedex_detail

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.widget.TextView
import com.google.android.material.chip.ChipGroup
import com.prmto.poxedex.R
import com.prmto.poxedex.domain.model.PokemonTypeWithColors

class PokemonChipTypeCreator(
    private val context: Context
) {
    private fun createPokemonChip(
        chipGroup: ChipGroup,
        pokemonTypeWithColor: PokemonTypeWithColors
    ): TextView {
        val pokemonChip = LayoutInflater.from(context).inflate(
            R.layout.poke_chip,
            chipGroup,
            false
        ) as TextView
        pokemonChip.text = pokemonTypeWithColor.name.lowercase().replaceFirstChar { it.uppercase() }
        val color = context.resources.getColor(pokemonTypeWithColor.color, null)
        pokemonChip.backgroundTintList = ColorStateList.valueOf(color)
        return pokemonChip
    }

    fun createPokemonChips(
        chipGroup: ChipGroup,
        pokemonTypeWithColors: List<PokemonTypeWithColors>
    ) {
        clearChips(chipGroup = chipGroup)
        pokemonTypeWithColors.forEach { pokemonTypeWithColor ->
            val pokemonChip = createPokemonChip(
                chipGroup = chipGroup,
                pokemonTypeWithColor = pokemonTypeWithColor
            )
            chipGroup.addView(pokemonChip)
        }
    }

    private fun clearChips(chipGroup: ChipGroup) {
        chipGroup.removeAllViews()
    }
}
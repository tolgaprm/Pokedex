package com.prmto.poxedex.data.mapper

import com.prmto.poxedex.common.Constants.POKEMON_FLAVOR_TEXT_LANGUAGE
import com.prmto.poxedex.data.dto.FlavorTextEntryDto

/*
This function retrieves a random flavor text because the design mockup in Figma displays different flavor texts.
 I couldn't identify a distinctive feature based on the Pokémon in the Figma design.
*/
fun List<FlavorTextEntryDto>.toFlavorText(): String {
    val flavorTextLanguageEn = this.filter { it.language.name == POKEMON_FLAVOR_TEXT_LANGUAGE }
    val flavorText = flavorTextLanguageEn.random()
    return flavorText.flavorText.replace("\n", " ")
}



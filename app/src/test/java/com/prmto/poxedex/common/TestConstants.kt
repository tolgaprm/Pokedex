package com.prmto.poxedex.common

import com.prmto.poxedex.data.dto.AllPokemonResponse
import com.prmto.poxedex.data.dto_generator.abilityDto
import com.prmto.poxedex.data.dto_generator.flavorTextEntryDto
import com.prmto.poxedex.data.dto_generator.languageDto
import com.prmto.poxedex.data.dto_generator.pokemonDetailDto
import com.prmto.poxedex.data.dto_generator.pokemonSpeciesDto
import com.prmto.poxedex.data.dto_generator.statDto
import com.prmto.poxedex.data.dto_generator.typeDto
import com.prmto.poxedex.domain.model_generator.singlePokemon

object TestConstants {

    val ALL_POKEMON_RESPONSE_PAGE_0 = AllPokemonResponse(
        count = 3,
        next = "testpage1",
        previous = null,
        results = listOf(
            singlePokemon(name = "ıvysaur", id = 2),
            singlePokemon(name = "balbasaur", id = 1),
            singlePokemon(name = "venusaur", id = 3),
        )
    )

    val ALL_POKEMON_RESPONSE_PAGE_1 = AllPokemonResponse(
        count = 3,
        next = "testpage2",
        previous = null,
        results = listOf(
            singlePokemon(name = "spearow", id = 21),
            singlePokemon(name = "fearow", id = 22),
            singlePokemon(name = "ekans", id = 23),
        )
    )

    val POKEMON_DETAILS_RESPONSE = listOf(
        pokemonDetailDto(
            id = 1,
            name = "bulbasaur",
            types = listOf(typeDto("grass"), typeDto("poison")),
            stats = listOf(
                statDto(45, "hp"),
                statDto(49, "attack"),
                statDto(49, "defense"),
                statDto(65, "special-attack"),
                statDto(65, "special-defense"),
                statDto(45, "speed")
            ),
            abilities = listOf(
                abilityDto("overgrow"),
                abilityDto("chlorophyll")
            ),
            height = 7,
            weight = 69
        ),
        pokemonDetailDto(
            id = 5,
            name = "charmeleon",
            types = listOf(typeDto("fire")),
            stats = listOf(
                statDto(58, "hp"),
                statDto(64, "attack"),
                statDto(58, "defense"),
                statDto(80, "special-attack"),
                statDto(65, "special-defense"),
                statDto(80, "speed")
            ),
            abilities = listOf(
                abilityDto("blaze"),
                abilityDto("solar-power")
            ),
            height = 11,
            weight = 190
        ),
        pokemonDetailDto(
            id = 25,
            name = "pikachu",
            types = listOf(typeDto("electric")),
            stats = listOf(
                statDto(35, "hp"),
                statDto(55, "attack"),
                statDto(40, "defense"),
                statDto(50, "special-attack"),
                statDto(50, "special-defense"),
                statDto(90, "speed")
            ),
            abilities = listOf(
                abilityDto("static"),
                abilityDto("lightning-rod")
            ),
            height = 4,
            weight = 60
        )
    )

    val POKEMON_SPECIES_RESPONSE = listOf(
        pokemonSpeciesDto(
            id = 25,
            name = "pikachu",
            flavorTexts = listOf(
                flavorTextEntryDto(
                    flavorText = "pikachu\nflavor1\nen",
                    language = languageDto(name = "en"),
                    version = 10
                ),
                flavorTextEntryDto(
                    flavorText = "pikachu_flavor_2_tr",
                    language = languageDto("tr")
                ),
                flavorTextEntryDto(
                    flavorText = "pikachu_flavor_3_en",
                    language = languageDto("en"),
                    version = 11
                ),
                flavorTextEntryDto(
                    flavorText = "pikachu_flavor_4_en",
                    language = languageDto("en"),
                    version = 10
                )
            )
        ),
        pokemonSpeciesDto(
            id = 5,
            name = "charmeleon",
            flavorTexts = listOf(
                flavorTextEntryDto(
                    flavorText = "charmeleon_flavor_1_en",
                    language = languageDto(name = "en")
                ),
                flavorTextEntryDto(
                    flavorText = "charmeleon_flavor_2_tr",
                    language = languageDto("tr")
                ),
                flavorTextEntryDto(
                    flavorText = "charmeleon_flavor_3_en",
                    language = languageDto("en")
                )
            )
        ),
        pokemonSpeciesDto(
            id = 1,
            name = "bulbasaur",
            flavorTexts = listOf(
                flavorTextEntryDto(
                    flavorText = "bulbasaur_flavor_1_en",
                    language = languageDto(name = "en")
                ),
                flavorTextEntryDto(
                    flavorText = "bulbasaur_flavor_2_tr",
                    language = languageDto("tr")
                ),
                flavorTextEntryDto(
                    flavorText = "bulbasaur_flavor_3_en",
                    language = languageDto("en")
                )
            )
        )
    )
}
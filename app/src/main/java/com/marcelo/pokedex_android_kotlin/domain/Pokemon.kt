package com.marcelo.pokedex_android_kotlin.domain

import com.marcelo.pokedex_android_kotlin.api.model.PokemonAbility

data class Pokemon(
    val id: String,
    val name: String,
    val weight: String,
    val height: String,
    val base_experience: String,
    val types: List<PokemonType>,
    val abilities: List<PokemonAbility>,
    val species: String
) {
    val formattedNumber = id.toString().padStart(3, '0')
    val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

}


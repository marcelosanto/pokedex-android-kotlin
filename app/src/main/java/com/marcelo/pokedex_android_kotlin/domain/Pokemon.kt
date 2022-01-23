package com.marcelo.pokedex_android_kotlin.domain

data class Pokemon(
    val id: String,
    val name: String,
    val types: List<PokemonType>
) {
    val formattedNumber = id.toString().padStart(3, '0')
    val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

}


package com.marcelo.pokedex_android_kotlin.data.model

data class ModelPokemon(
    val id: String,
    val name: String,
    val weight: String,
    val height: String,
    val base_experience: String,
    val types: List<PokemonType>,
    val abilities: List<PokemonAbility>,
    val species: String,
    val biography: String,
    val base_happiness: String,
    val capture_rate: String,
    val growth_rate: Rate,
    val evolutions: PokemonEvolutions,
    val stats: List<Stats>
) : java.io.Serializable {
    val formattedNumber = id.padStart(3, '0')
    val imageUrl =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

}


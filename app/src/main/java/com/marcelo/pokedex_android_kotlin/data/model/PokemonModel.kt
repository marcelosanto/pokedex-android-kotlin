package com.marcelo.pokedex_android_kotlin.data.model

data class PokemonModel(
    var id: String,
    var name: String,
    var imageUrl: String,
    var types: List<PokemonType>,
    val weight: String,
    val height: String,
    val base_experience: String,
    val abilities: List<PokemonAbility>,
    val species: String,
    val biography: String,
    val base_happiness: String,
    val capture_rate: String,
    val growth_rate: Rate,
    val evolutions: PokemonEvolutions,
    val stats: List<Stats>
)

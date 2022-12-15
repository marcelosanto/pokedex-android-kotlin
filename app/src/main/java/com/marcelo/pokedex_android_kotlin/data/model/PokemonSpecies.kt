package com.marcelo.pokedex_android_kotlin.data.model


data class PokemonSpecies(
    val genera: List<Genera>,
    val base_happiness: String,
    val capture_rate: String,
    val evolution_chain: Url,
    val flavor_text_entries: List<FlavorText>,
    val growth_rate: Rate
) : java.io.Serializable


data class Genera(
    val genus: String
) : java.io.Serializable


data class Url(
    val url: String
) : java.io.Serializable


data class FlavorText(
    val flavor_text: String
) : java.io.Serializable


data class Rate(
    val name: String
) : java.io.Serializable
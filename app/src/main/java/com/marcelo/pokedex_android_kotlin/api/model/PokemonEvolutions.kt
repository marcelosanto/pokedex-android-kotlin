package com.marcelo.pokedex_android_kotlin.api.model


data class PokemonEvolutions(
    val chain: Chain? = null
) : java.io.Serializable


data class Chain(
    val evolves_to: List<Evolves>? = null,
    val species: Species? = null
) : java.io.Serializable


data class Species(
    val name: String? = null,
    val url: String? = null
) : java.io.Serializable


data class Evolves(
    val evolution_details: List<DetailsEvo>? = null,
    val species: Species? = null,
    val evolves_to: List<Evolu>? = null
) : java.io.Serializable


data class Evolu(
    val evolution_details: List<DetailsEvo>? = null,
    val species: Species? = null
) : java.io.Serializable


data class DetailsEvo(
    val min_level: String? = null
) : java.io.Serializable



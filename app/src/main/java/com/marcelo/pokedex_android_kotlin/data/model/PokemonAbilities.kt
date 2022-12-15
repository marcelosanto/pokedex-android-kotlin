package com.marcelo.pokedex_android_kotlin.data.model


data class PokemonAbilities(
    val ability: PokemonAbility,
    val is_hidden: Boolean,
    val slot: Int
) : java.io.Serializable


data class PokemonAbility(
    val name: String
) : java.io.Serializable
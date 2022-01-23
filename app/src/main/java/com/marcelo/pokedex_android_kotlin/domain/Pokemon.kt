package com.marcelo.pokedex_android_kotlin.domain

data class Pokemon (
    val imageUrl: String,
    val number: Int,
    val name: String,
    val types: List<PokemonType>
        )


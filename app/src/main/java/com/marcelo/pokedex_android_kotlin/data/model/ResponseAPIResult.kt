package com.marcelo.pokedex_android_kotlin.data.model

data class ResponseAPIResult(
    val count: Int,
    val next: String?,
    //val previous: Any,
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)


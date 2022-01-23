package com.marcelo.pokedex_android_kotlin.api.model

import com.marcelo.pokedex_android_kotlin.domain.PokemonType

data class PokemonApiResult(
    val id: String,
    val name: String,
    val types: List<PokemonTypeSlot>
)

data class PokemonTypeSlot(
    val slot: Int,
    val type: PokemonType
)

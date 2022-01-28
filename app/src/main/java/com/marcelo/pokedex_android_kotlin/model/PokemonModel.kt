package com.marcelo.pokedex_android_kotlin.model

import android.os.Parcelable
import com.marcelo.pokedex_android_kotlin.api.model.PokemonAbility
import com.marcelo.pokedex_android_kotlin.domain.PokemonType
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonModel(
    var id: String,
    var name: String,
    var imageUrl: String,
    var types: List<PokemonType>,
    val weight: String,
    val height: String,
    val base_experience: String,
    val abilities: List<PokemonAbility>,
    val species: String
) : Parcelable

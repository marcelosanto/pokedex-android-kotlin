package com.marcelo.pokedex_android_kotlin.model

import android.os.Parcelable
import com.marcelo.pokedex_android_kotlin.domain.PokemonType
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonModel(
    var id: String,
    var name: String,
    var imageUrl: String,
    var types: List<PokemonType>
) : Parcelable

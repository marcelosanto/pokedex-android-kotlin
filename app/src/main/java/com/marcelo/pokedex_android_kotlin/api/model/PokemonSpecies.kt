package com.marcelo.pokedex_android_kotlin.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonSpecies(
    val genera: List<Genera>

) : Parcelable

@Parcelize
data class Genera(
    val genus: String
) : Parcelable


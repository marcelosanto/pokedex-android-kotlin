package com.marcelo.pokedex_android_kotlin.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonType(
    val name: String
) : Parcelable
package com.marcelo.pokedex_android_kotlin.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonSpecies(
    val genera: List<Genera>,
    val base_happiness: String,
    val capture_rate: String,
    val evolution_chain: Url,
    val flavor_text_entries: List<FlavorText>,
    val growth_rate: Rate
) : Parcelable

@Parcelize
data class Genera(
    val genus: String
) : Parcelable

@Parcelize
data class Url(
    val url: String
) : Parcelable

@Parcelize
data class FlavorText(
    val flavor_text: String
) : Parcelable

@Parcelize
data class Rate(
    val name: String
) : Parcelable
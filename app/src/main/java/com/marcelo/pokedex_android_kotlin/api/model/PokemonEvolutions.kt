package com.marcelo.pokedex_android_kotlin.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonEvolutions(
    val chain: Chain? = null
) : Parcelable

@Parcelize
data class Chain(
    val evolves_to: List<Evolves>? = null,
    val species: Species? = null
) : Parcelable

@Parcelize
data class Species(
    val name: String? = null,
    val url: String? = null
) : Parcelable

@Parcelize
data class Evolves(
    val evolution_details: List<DetailsEvo>? = null,
    val species: Species? = null,
    val evolves_to: List<Evolu>? = null
) : Parcelable

@Parcelize
data class Evolu(
    val evolution_details: List<DetailsEvo>? = null,
    val species: Species? = null
) : Parcelable

@Parcelize
data class DetailsEvo(
    val min_level: String? = null
) : Parcelable



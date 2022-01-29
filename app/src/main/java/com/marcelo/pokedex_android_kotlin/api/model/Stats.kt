package com.marcelo.pokedex_android_kotlin.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stats(
    val base_stat: Int,
    val effort: Int,
    val stat: Stat
) : Parcelable

@Parcelize
data class Stat(
    val name: String
) : Parcelable
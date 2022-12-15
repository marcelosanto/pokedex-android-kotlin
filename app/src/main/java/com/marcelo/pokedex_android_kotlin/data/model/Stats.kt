package com.marcelo.pokedex_android_kotlin.data.model


data class Stats(
    val base_stat: Int,
    val effort: Int,
    val stat: Stat
) : java.io.Serializable


data class Stat(
    val name: String
) : java.io.Serializable
package com.marcelo.pokedex_android_kotlin.domain.repository

import com.marcelo.pokedex_android_kotlin.data.model.Pokemon
import com.marcelo.pokedex_android_kotlin.data.model.PokemonEvolutions
import com.marcelo.pokedex_android_kotlin.data.model.PokemonSpecies
import com.marcelo.pokedex_android_kotlin.data.model.ResponseAPIResult
import retrofit2.Response

interface PokemonRepository {
    suspend fun getAllPokemons(offset: Int): Response<ResponseAPIResult>

    suspend fun getPokemon(id: Int): Response<Pokemon>

    suspend fun getPokemonSpecies(id: Int): Response<PokemonSpecies>

    suspend fun getPokemonEvolutions(id: Int): Response<PokemonEvolutions>

}
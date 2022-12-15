package com.marcelo.pokedex_android_kotlin.data.api

import com.marcelo.pokedex_android_kotlin.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun listPokemons(@Query("offset") offset: Int): Response<ResponseAPIResult>

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): Response<Pokemon>

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(@Path("id") id: Int): Response<PokemonSpecies>

    @GET("evolution-chain/{number}")
    suspend fun getPokemonEvolutions(@Path("number") number: Int): Response<PokemonEvolutions>
}
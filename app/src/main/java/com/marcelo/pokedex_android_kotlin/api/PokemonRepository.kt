package com.marcelo.pokedex_android_kotlin.api

import com.marcelo.pokedex_android_kotlin.api.model.PokemonApiResult
import com.marcelo.pokedex_android_kotlin.api.model.PokemonsApiResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokemonRepository {
    // https://pokeapi.co/api/v2/pokemon/?limit=151

    private val service: PokemonService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()

        service = retrofit.create(PokemonService::class.java)

    }

    fun getListPokemons(limit: Int = 151): PokemonsApiResult? {
        val call = service.listPokemons(limit)

        return call.execute().body()
    }

    fun getPokemon(id: Int): PokemonApiResult? {
        val call = service.getPokemon(id)

        return call.execute().body()
    }
}
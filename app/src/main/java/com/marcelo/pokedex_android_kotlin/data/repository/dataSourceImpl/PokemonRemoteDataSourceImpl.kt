package com.marcelo.pokedex_android_kotlin.data.repository.dataSourceImpl

import com.marcelo.pokedex_android_kotlin.data.api.PokemonService
import com.marcelo.pokedex_android_kotlin.data.model.Pokemon
import com.marcelo.pokedex_android_kotlin.data.model.PokemonEvolutions
import com.marcelo.pokedex_android_kotlin.data.model.PokemonSpecies
import com.marcelo.pokedex_android_kotlin.data.model.ResponseAPIResult
import com.marcelo.pokedex_android_kotlin.data.repository.dataSource.PokemonRemoteDataSource
import retrofit2.Response

class PokemonRemoteDataSourceImpl(private val pokemonService: PokemonService) :
    PokemonRemoteDataSource {
    override suspend fun getAllPokemons(offset: Int): Response<ResponseAPIResult> {
        return pokemonService.listPokemons(offset)
    }

    override suspend fun getPokemon(id: Int): Response<Pokemon> {
        return pokemonService.getPokemon(id)
    }

    override suspend fun getPokemonSpecies(id: Int): Response<PokemonSpecies> {
        return pokemonService.getPokemonSpecies(id)
    }

    override suspend fun getPokemonEvolutions(id: Int): Response<PokemonEvolutions> {
        return pokemonService.getPokemonEvolutions(id)
    }


}

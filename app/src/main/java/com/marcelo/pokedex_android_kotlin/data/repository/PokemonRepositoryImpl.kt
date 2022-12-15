package com.marcelo.pokedex_android_kotlin.data.repository

import com.marcelo.pokedex_android_kotlin.data.model.Pokemon
import com.marcelo.pokedex_android_kotlin.data.model.PokemonEvolutions
import com.marcelo.pokedex_android_kotlin.data.model.PokemonSpecies
import com.marcelo.pokedex_android_kotlin.data.model.ResponseAPIResult
import com.marcelo.pokedex_android_kotlin.data.repository.dataSource.PokemonRemoteDataSource
import com.marcelo.pokedex_android_kotlin.domain.repository.PokemonRepository
import retrofit2.Response

class PokemonRepositoryImpl(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
) : PokemonRepository {
    override suspend fun getAllPokemons(offset: Int): Response<ResponseAPIResult> {
        return pokemonRemoteDataSource.getAllPokemons(offset)
    }

    override suspend fun getPokemon(id: Int): Response<Pokemon> {
        return pokemonRemoteDataSource.getPokemon(id)
    }

    override suspend fun getPokemonSpecies(id: Int): Response<PokemonSpecies> {
        return pokemonRemoteDataSource.getPokemonSpecies(id)
    }

    override suspend fun getPokemonEvolutions(id: Int): Response<PokemonEvolutions> {
        return pokemonRemoteDataSource.getPokemonEvolutions(id)
    }


}
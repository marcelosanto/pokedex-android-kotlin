package com.marcelo.pokedex_android_kotlin.domain.usecase

import com.marcelo.pokedex_android_kotlin.data.model.Pokemon
import com.marcelo.pokedex_android_kotlin.data.model.PokemonEvolutions
import com.marcelo.pokedex_android_kotlin.data.model.PokemonSpecies
import com.marcelo.pokedex_android_kotlin.data.model.ResponseAPIResult
import com.marcelo.pokedex_android_kotlin.domain.repository.PokemonRepository
import retrofit2.Response

class GetAllPokemons(private val pokemonRepository: PokemonRepository) {
    suspend fun execute(offset: Int): Response<ResponseAPIResult> {
        return pokemonRepository.getAllPokemons(offset)
    }

    suspend fun pokemonInfo(id: Int): Response<Pokemon> {
        return pokemonRepository.getPokemon(id)
    }

    suspend fun pokemonSpecies(id: Int): Response<PokemonSpecies> {
        return pokemonRepository.getPokemonSpecies(id)
    }

    suspend fun pokemonEvolutions(id: Int): Response<PokemonEvolutions> {
        return pokemonRepository.getPokemonEvolutions(id)
    }


}
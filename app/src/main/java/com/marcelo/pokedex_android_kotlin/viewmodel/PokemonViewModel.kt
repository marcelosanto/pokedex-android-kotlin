package com.marcelo.pokedex_android_kotlin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marcelo.pokedex_android_kotlin.api.PokemonRepository
import com.marcelo.pokedex_android_kotlin.domain.Pokemon

class PokemonViewModel : ViewModel() {
    var pokemons = MutableLiveData<List<Pokemon?>>()

    init {
        Thread(Runnable {
            loadPokemons()
        }).start()
    }

    private fun loadPokemons() {
        val pokemonsApiResult = PokemonRepository.getListPokemons()

        pokemonsApiResult?.results?.let {
            pokemons.postValue(it.map { pokemonResult ->
                val id = pokemonResult.url
                    .replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "").toInt()

                val pokemonApiResult = PokemonRepository.getPokemon(id)
                val pokemonSpecies = PokemonRepository.getPokemonSpecies(id)
                var ss = pokemonSpecies?.genera!![7].genus.toString()
                Log.w("EEEE", "loadPokemons: $ss")

                pokemonApiResult?.let {
                    Pokemon(
                        pokemonApiResult.id,
                        pokemonApiResult.name,
                        pokemonApiResult.weight,
                        pokemonApiResult.height,
                        pokemonApiResult.base_experience,
                        pokemonApiResult.types.map { type ->
                            type.type
                        },
                        pokemonApiResult.abilities.map { ability -> ability.ability },
                        "$ss"

                    )

                }
            })
        }
    }
}
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

                var species = pokemonSpecies?.genera!![7].genus
                var flavor_text = pokemonSpecies?.flavor_text_entries[7].flavor_text
                var base_happiness = pokemonSpecies?.base_happiness
                var capture_rate = pokemonSpecies?.capture_rate
                var growthRate = pokemonSpecies?.growth_rate

                Log.w("EEEE", "loadPokemons: ${pokemonSpecies?.growth_rate.name}")

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
                        "$species",
                        "$flavor_text",
                        "$base_happiness",
                        "$capture_rate",
                        growthRate

                    )

                }
            })
        }
    }
}
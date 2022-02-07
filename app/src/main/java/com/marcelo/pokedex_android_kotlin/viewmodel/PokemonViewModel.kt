package com.marcelo.pokedex_android_kotlin.viewmodel

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
        val pokemonsApiResult = PokemonRepository.getListPokemons(151)

        pokemonsApiResult?.results?.let { it ->
            pokemons.postValue(it.map { pokemonResult ->
                val id = pokemonResult.url
                    .replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "").toInt()

                val pokemonApiResult = PokemonRepository.getPokemon(id)
                val pokemonSpecies = PokemonRepository.getPokemonSpecies(id)

                var species = pokemonSpecies?.genera!![7].genus
                var flavorText = pokemonSpecies.flavor_text_entries[7].flavor_text
                var baseHappiness = pokemonSpecies.base_happiness
                var captureRate = pokemonSpecies.capture_rate
                var growthRate = pokemonSpecies.growth_rate

                var evolutionUrl = pokemonSpecies.evolution_chain.url
                    .replace("https://pokeapi.co/api/v2/evolution-chain/", "")
                    .replace("/", "").toInt()

                val pokemonEvolucao = PokemonRepository.getPokemonEvolutions(evolutionUrl)

                //Log.w("EEEE", "loadPokemons: $pokemonEvolucao")

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
                        species,
                        flavorText,
                        baseHappiness,
                        captureRate,
                        growthRate,
                        pokemonEvolucao!!,
                        pokemonApiResult.stats

                    )

                }
            })
        }
    }
}
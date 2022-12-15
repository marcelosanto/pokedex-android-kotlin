package com.marcelo.pokedex_android_kotlin.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.marcelo.pokedex_android_kotlin.data.model.ModelPokemon
import com.marcelo.pokedex_android_kotlin.domain.usecase.GetAllPokemons
import com.marcelo.pokedex_android_kotlin.utils.Const.findId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewModel(private val app: Application, private val getAllPokemons: GetAllPokemons) :
    AndroidViewModel(app) {
    val pokemons: MutableLiveData<List<ModelPokemon?>> = MutableLiveData()

    fun getAllPokemons(offset: Int) = viewModelScope.launch(Dispatchers.IO) {
        val apiResult = getAllPokemons.execute(offset)
        val resul = apiResult.body()?.results

        resul.let {
            pokemons.postValue(it?.map {
                val pokemon = getAllPokemons.pokemonInfo(findId(it.url))
                val pokemonSpecies = getAllPokemons.pokemonSpecies(findId(it.url)).body()
                val pokemonEvolutions =
                    getAllPokemons.pokemonEvolutions(findId(pokemonSpecies?.evolution_chain!!.url))
                        .body()

                var species = pokemonSpecies.genera[7].genus
                var flavorText = pokemonSpecies.flavor_text_entries[7].flavor_text
                var baseHappiness = pokemonSpecies.base_happiness
                var captureRate = pokemonSpecies.capture_rate
                var growthRate = pokemonSpecies.growth_rate

                pokemon.body().let {
                    it?.let { pokemon ->
                        ModelPokemon(
                            pokemon.id,
                            pokemon.name,
                            pokemon.weight,
                            pokemon.height,
                            pokemon.base_experience,
                            pokemon.types.map { type ->
                                type.type
                            },
                            pokemon.abilities.map { ability -> ability.ability },
                            species,
                            flavorText,
                            baseHappiness,
                            captureRate,
                            growthRate,
                            pokemonEvolutions!!,
                            pokemon.stats

                        )
                    }

                }
            })
        }


    }


    fun search(query: String) {
        //differ.submitList(tempList.filter { it.name.contains(query, true) })
    }


//    private fun loadPokemons() {
//        val pokemonsApiResult = PokemonRepository.getListPokemons(15)
//
//        if (pokemonsApiResult != null) {
//            pokemonsApiResult.results.let { it ->
//                pokemons.postValue(it.map { pokemonResult ->
//                    val id = pokemonResult.url
//                        .replace("https://pokeapi.co/api/v2/pokemon/", "")
//                        .replace("/", "").toInt()
//
//                    val pokemonApiResult = PokemonRepository.getPokemon(id)
//                    val pokemonSpecies = PokemonRepository.getPokemonSpecies(id)
//
//                    var species = pokemonSpecies?.genera!![7].genus
//                    var flavorText = pokemonSpecies.flavor_text_entries[7].flavor_text
//                    var baseHappiness = pokemonSpecies.base_happiness
//                    var captureRate = pokemonSpecies.capture_rate
//                    var growthRate = pokemonSpecies.growth_rate
//
//                    var evolutionUrl = pokemonSpecies.evolution_chain.url
//                        .replace("https://pokeapi.co/api/v2/evolution-chain/", "")
//                        .replace("/", "").toInt()
//
//                    val pokemonEvolucao = PokemonRepository.getPokemonEvolutions(evolutionUrl)
//
//                    //Log.w("EEEE", "loadPokemons: $pokemonEvolucao")
//
//                    pokemonApiResult?.let {
//                        Pokemon(
//                            pokemonApiResult.id,
//                            pokemonApiResult.name,
//                            pokemonApiResult.weight,
//                            pokemonApiResult.height,
//                            pokemonApiResult.base_experience,
//                            pokemonApiResult.types.map { type ->
//                                type.type
//                            },
//                            pokemonApiResult.abilities.map { ability -> ability.ability },
//                            species,
//                            flavorText,
//                            baseHappiness,
//                            captureRate,
//                            growthRate,
//                            pokemonEvolucao!!,
//                            pokemonApiResult.stats
//
//                        )
//
//                    }
//                })
//            }
//        }
//    }


}
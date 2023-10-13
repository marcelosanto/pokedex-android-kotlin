package com.marcelo.pokedex_android_kotlin.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.marcelo.pokedex_android_kotlin.data.model.ModelPokemon
import com.marcelo.pokedex_android_kotlin.domain.usecase.GetAllPokemons
import com.marcelo.pokedex_android_kotlin.utils.Const.getIdByURL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewModel(private val app: Application, private val getAllPokemons: GetAllPokemons) :
    AndroidViewModel(app) {
    val pokemons: MutableLiveData<List<ModelPokemon?>> = MutableLiveData()

    fun getAllPokemons(offset: Int) = CoroutineScope(Dispatchers.IO).launch {
        val apiResult = getAllPokemons.execute(offset)
        val resul = apiResult.body()?.results

        resul.let {
            pokemons.postValue(it?.map {
                val pokemon = getAllPokemons.pokemonInfo(getIdByURL(it.url))
                val pokemonSpecies = getAllPokemons.pokemonSpecies(getIdByURL(it.url)).body()
                val pokemonEvolutions =
                    getAllPokemons.pokemonEvolutions(getIdByURL(pokemonSpecies?.evolution_chain!!.url))
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

    fun filterList(filter: String, list: MutableList<ModelPokemon>): MutableList<ModelPokemon> {
        val filteredList = mutableListOf<ModelPokemon>()
        for (item in list) {
            if (item.name.contains(filter, ignoreCase = true) || item.id.contains(
                    filter,
                    ignoreCase = true
                )
            ) {
                filteredList.add(item)
            }
        }
        
        return filteredList
    }


}
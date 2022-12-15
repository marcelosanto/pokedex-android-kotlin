package com.marcelo.pokedex_android_kotlin.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marcelo.pokedex_android_kotlin.domain.usecase.GetAllPokemons

class PokemonViewModelFactory(
    private val app: Application,
    private val getAllPokemons: GetAllPokemons
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PokemonViewModel(app, getAllPokemons) as T
    }
}
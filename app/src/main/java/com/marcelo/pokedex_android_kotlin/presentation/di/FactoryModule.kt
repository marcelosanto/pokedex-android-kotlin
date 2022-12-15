package com.marcelo.pokedex_android_kotlin.presentation.di

import android.app.Application
import com.marcelo.pokedex_android_kotlin.domain.usecase.GetAllPokemons
import com.marcelo.pokedex_android_kotlin.presentation.viewmodel.PokemonViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Provides
    @Singleton
    fun providesPokemonViewModelFactory(
        application: Application,
        getAllPokemons: GetAllPokemons
    ): PokemonViewModelFactory {
        return PokemonViewModelFactory(application, getAllPokemons)
    }
}
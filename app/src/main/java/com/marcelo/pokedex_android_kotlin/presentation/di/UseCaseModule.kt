package com.marcelo.pokedex_android_kotlin.presentation.di

import com.marcelo.pokedex_android_kotlin.domain.repository.PokemonRepository
import com.marcelo.pokedex_android_kotlin.domain.usecase.GetAllPokemons
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetAllPokemons(pokemonRepository: PokemonRepository): GetAllPokemons {
        return GetAllPokemons(pokemonRepository)
    }
}
package com.marcelo.pokedex_android_kotlin.presentation.di

import com.marcelo.pokedex_android_kotlin.data.repository.PokemonRepositoryImpl
import com.marcelo.pokedex_android_kotlin.data.repository.dataSource.PokemonRemoteDataSource
import com.marcelo.pokedex_android_kotlin.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesPokemonRepository(pokemonRemoteDataSource: PokemonRemoteDataSource): PokemonRepository {
        return PokemonRepositoryImpl(pokemonRemoteDataSource)
    }
}
package com.marcelo.pokedex_android_kotlin.presentation.di

import com.marcelo.pokedex_android_kotlin.data.api.PokemonService
import com.marcelo.pokedex_android_kotlin.data.repository.dataSource.PokemonRemoteDataSource
import com.marcelo.pokedex_android_kotlin.data.repository.dataSourceImpl.PokemonRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun providesPokemonRemoteDataSource(pokemonService: PokemonService): PokemonRemoteDataSource {
        return PokemonRemoteDataSourceImpl(pokemonService)
    }
}
package com.marcelo.pokedex_android_kotlin.presentation.di

import android.util.Log
import com.google.gson.GsonBuilder
import com.marcelo.pokedex_android_kotlin.data.api.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Singleton
    @Provides
    fun providePokemonService(retrofit: Retrofit): PokemonService {
        Log.i("TAG", "pokemonURL: ${retrofit.baseUrl()}")
        return retrofit.create(PokemonService::class.java)
    }
}
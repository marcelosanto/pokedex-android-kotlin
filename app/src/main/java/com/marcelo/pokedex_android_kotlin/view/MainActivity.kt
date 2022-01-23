package com.marcelo.pokedex_android_kotlin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.pokedex_android_kotlin.R
import com.marcelo.pokedex_android_kotlin.domain.Pokemon
import com.marcelo.pokedex_android_kotlin.domain.PokemonType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvPokemons)

        val fearow = Pokemon(
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/22.png",
            22,
            "fearow",
            listOf(PokemonType("normal"), PokemonType("flying"))
        )

        val pokemons = listOf(
            fearow, fearow, fearow, fearow, fearow, fearow, fearow, fearow, fearow, fearow
        )

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = PokemonAdapter(pokemons)
    }
}
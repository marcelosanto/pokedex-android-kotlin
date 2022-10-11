package com.marcelo.pokedex_android_kotlin.pokedex.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.marcelo.pokedex_android_kotlin.R

class PokedexActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)
    }
}
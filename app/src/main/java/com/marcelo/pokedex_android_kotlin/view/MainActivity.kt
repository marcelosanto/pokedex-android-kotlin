package com.marcelo.pokedex_android_kotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marcelo.pokedex_android_kotlin.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
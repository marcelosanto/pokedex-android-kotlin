package com.marcelo.pokedex_android_kotlin.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marcelo.pokedex_android_kotlin.R
import com.marcelo.pokedex_android_kotlin.model.PokemonModel

private const val ARG_PARAM1 = "message"
private const val ARG_PARAM2 = "name"

class PokemonAboutFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bundle = arguments
        val message = bundle!!.getParcelable<PokemonModel>("message")
        Log.i("TAG", "About: ${message}")

        return inflater.inflate(R.layout.fragment_pokemon_about, container, false)
    }


}
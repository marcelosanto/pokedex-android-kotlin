package com.marcelo.pokedex_android_kotlin.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marcelo.pokedex_android_kotlin.R
import com.marcelo.pokedex_android_kotlin.model.PokemonModel

class StatsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = arguments
        val message = bundle!!.getParcelable<PokemonModel>("message")
        Log.i("TAG", "Stats: ${message?.name}")
        
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false)


    }


}
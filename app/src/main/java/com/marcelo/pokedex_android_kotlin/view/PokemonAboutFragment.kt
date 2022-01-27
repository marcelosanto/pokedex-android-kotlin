package com.marcelo.pokedex_android_kotlin.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

        return inflater.inflate(R.layout.fragment_pokemon_about, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        val pokemon = bundle!!.getParcelable<PokemonModel>("message")
        Log.i("TAG", "About: ${pokemon?.base_experience}")

        val txtAbilities: TextView = view.findViewById(R.id.txt_abilities)
        val txtBaseExp: TextView = view.findViewById(R.id.txt_base_exp)
        val txtHeight: TextView = view.findViewById(R.id.txt_height)
        val txtWeight: TextView = view.findViewById(R.id.txt_weight)

        pokemon.let {
            txtAbilities.text =
                "${it?.abilities?.get(0)?.name?.capitalize()}, ${it?.abilities?.get(1)?.name?.capitalize()}"
            txtBaseExp.text = it?.base_experience

            if (it?.height?.toInt()!! > 1) {
                var he = it?.height?.toInt() / 10
                txtHeight.text = "$he m"
            } else {
                txtHeight.text = "${it?.height?.toInt()} cm"
            }

            txtWeight.text = it?.weight
        }

    }

}
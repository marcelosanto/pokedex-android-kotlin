package com.marcelo.pokedex_android_kotlin.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.marcelo.pokedex_android_kotlin.R
import com.marcelo.pokedex_android_kotlin.model.PokemonModel
import java.util.*


class PokemonAboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_pokemon_about, container, false)

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        val pokemon = bundle!!.getParcelable<PokemonModel>("message")

        val list = weaknessPokemon(pokemon!!.types[0].name)

        val txtSpecies: TextView = view.findViewById(R.id.txt_species)
        val txtAbilities: TextView = view.findViewById(R.id.txt_abilities)
        val txtBaseExp: TextView = view.findViewById(R.id.txt_base_exp)
        val txtHeight: TextView = view.findViewById(R.id.txt_height)
        val txtWeight: TextView = view.findViewById(R.id.txt_weight)

        val txtWeakness0: TextView = view.findViewById(R.id.txt_weakness0)
        val txtWeakness1: TextView = view.findViewById(R.id.txt_weakness1)
        val txtWeakness2: TextView = view.findViewById(R.id.txt_weakness2)
        val txtWeakness3: TextView = view.findViewById(R.id.txt_weakness3)
        val txtWeakness4: TextView = view.findViewById(R.id.txt_weakness4)

        for (item in list.indices) {

            if (item == 0) txtWeakness0.visibility = View.GONE
            if (item == 1) txtWeakness1.visibility = View.GONE
            if (item == 2) txtWeakness2.visibility = View.GONE
            if (item == 3) txtWeakness3.visibility = View.GONE
            if (item == 4) txtWeakness4.visibility = View.GONE

        }

        Log.d("SIZE", "onViewCreated: ${pokemon.abilities.size}")



        pokemon?.let {

            txtSpecies.text = "${captalizerText(it.species)}"

            txtAbilities.text = when (it.abilities.size) {
                1 -> "${captalizerText(it.abilities.get(0).name)}"
                2 -> "${captalizerText(it.abilities.get(0).name)}, ${
                    captalizerText(
                        it.abilities.get(
                            1
                        ).name
                    )
                }"
                else -> ""
            }
                

            txtBaseExp.text = it.base_experience

            if (it.height.toDouble() / 10 >= 1) {
                txtHeight.text = "${it.height.toDouble() / 10} m"
            } else {
                txtHeight.text = "${it.height.toDouble() / 10} cm"
            }
            txtWeight.text = "${it.weight.toDouble() / 10} Kg"
        }

    }

}

fun captalizerText(text: String) = text.replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(
        Locale.getDefault()
    ) else it.toString()
}

fun weaknessPokemon(type: String): List<String> = when (type) {
    "normal" -> listOf("Fight")


    "fire" -> listOf("Water", "Ground", "Rock")


    "water" -> listOf("Grass", "Electric")


    "grass" -> listOf("Fire", "Ice", "Poison", "Flying", "Bug")


    "electric" -> listOf("Ground")


    "ice" -> listOf("Fire", "Fighting", "Rock", "Steel")


    "fighting" -> listOf("Flying", "Psychic", "Fairy")


    "poison" -> listOf("Ground", "Psychic")


    "ground" -> listOf("Water", "Grass", "Ice")


    "flying" -> listOf("Electric", "Ice", "Rock")


    "psychic" -> listOf("Bug", "Ghost", "Dark")


    "bug" -> listOf("Flying", "Rock", "Fire")


    "rock" -> listOf("Water", "Grass", "Fighting", "Ground", "Steel")


    "ghost" -> listOf("Ghost", "Dark")


    "dragon" -> listOf("Ice", "Dragon", "Fairy")


    "dark" -> listOf("Fighting", "Bug", "Fairy")


    "steel" -> listOf("Fire", "Fighting", "Ground")


    "fairy" -> listOf("Poison", "Steel")


    else -> listOf("deu ruim")
}


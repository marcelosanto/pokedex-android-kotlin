package com.marcelo.pokedex_android_kotlin.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.marcelo.pokedex_android_kotlin.R
import com.marcelo.pokedex_android_kotlin.api.model.PokemonModel
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

        val txtBio: TextView = view.findViewById(R.id.txtBio)
        val txtSpecies: TextView = view.findViewById(R.id.txt_species)
        val txtAbilities: TextView = view.findViewById(R.id.txt_abilities)
        val txtBaseExp: TextView = view.findViewById(R.id.txt_base_exp)
        val txtHeight: TextView = view.findViewById(R.id.txt_height)
        val txtWeight: TextView = view.findViewById(R.id.txt_weight)
        val txtCatchRate: TextView = view.findViewById(R.id.txtCatchRate)
        val txtBaseFriend: TextView = view.findViewById(R.id.txtBaseFriend)
        val txtGrowthRate: TextView = view.findViewById(R.id.txtGrowthRate)

        val txtWeakness0: TextView = view.findViewById(R.id.txt_weakness0)
        val txtWeakness1: TextView = view.findViewById(R.id.txt_weakness1)
        val txtWeakness2: TextView = view.findViewById(R.id.txt_weakness2)
        val txtWeakness3: TextView = view.findViewById(R.id.txt_weakness3)
        val txtWeakness4: TextView = view.findViewById(R.id.txt_weakness4)

        for (item in list.indices) {

            if (item == 0) {
                txtWeakness0.visibility = View.VISIBLE
                setIconAndColorForTextView(list[item], txtWeakness0)
            }

            if (item == 1) {
                txtWeakness1.visibility = View.VISIBLE
                setIconAndColorForTextView(list[item], txtWeakness1)
            }

            if (item == 2) {
                txtWeakness2.visibility = View.VISIBLE
                setIconAndColorForTextView(list[item], txtWeakness2)
            }

            if (item == 3) {
                txtWeakness3.visibility = View.VISIBLE
                setIconAndColorForTextView(list[item], txtWeakness3)
            }

            if (item == 4) {
                txtWeakness4.visibility = View.VISIBLE
                setIconAndColorForTextView(list[item], txtWeakness4)
            }


        }

        pokemon?.let {

            txtBio.text = "${it.biography.replace("\n", " ")}"
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

            txtCatchRate.text = it.capture_rate
            txtBaseFriend.text = it.base_happiness
            txtGrowthRate.text = it.growth_rate.name
        }

    }

}

fun captalizerText(text: String) = text.replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(
        Locale.getDefault()
    ) else it.toString()
}

fun weaknessPokemon(type: String): List<String> = when (type) {
    "normal" -> listOf("fight")


    "fire" -> listOf("water", "ground", "rock")


    "water" -> listOf("grass", "electric")


    "grass" -> listOf("fire", "ice", "poison", "flying", "bug")


    "electric" -> listOf("ground")


    "ice" -> listOf("fire", "fighting", "rock", "steel")


    "fighting" -> listOf("flying", "psychic", "fairy")


    "poison" -> listOf("ground", "psychic")


    "ground" -> listOf("water", "grass", "ice")


    "flying" -> listOf("electric", "ice", "rock")


    "psychic" -> listOf("bug", "ghost", "dark")


    "bug" -> listOf("flying", "rock", "fire")


    "rock" -> listOf("water", "grass", "fighting", "ground", "steel")


    "ghost" -> listOf("ghost", "dark")


    "dragon" -> listOf("ice", "dragon", "fairy")


    "dark" -> listOf("fighting", "bug", "fairy")


    "steel" -> listOf("fire", "fighting", "ground")


    "fairy" -> listOf("poison", "steel")


    else -> listOf("deu ruim")
}

private fun setIconAndColorForTextView(
    type: String,
    txt: TextView,
) {
    when (type) {
        "flying" -> {
            txt.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_flying,
                0,
                0,
                0
            )
            txt.setBackgroundColor(Color.parseColor("#a385e0"))
        }
        "grass" -> {
            txt.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_grass,
                0,
                0,
                0
            )
            txt.setBackgroundColor(Color.parseColor("#56972f"))
        }
        "bug" -> {
            txt.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_bug,
                0,
                0,
                0
            )
            txt.setBackgroundColor(Color.parseColor("#6a7611"))
        }
        "poison" -> {
            txt.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_poison,
                0,
                0,
                0
            )
            txt.setBackgroundColor(Color.parseColor("#6c296a"))
        }
        "normal" -> {
            txt.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_normal,
                0,
                0,
                0
            )
            txt.setBackgroundColor(Color.parseColor("#818054"))
        }

        "dark" -> {
            txt.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_dark,
                0,
                0,
                0
            )
            txt.setBackgroundColor(Color.parseColor("#413229"))
        }
        "dragon" -> {
            txt.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_dragon,
                0,
                0,
                0
            )
            txt.setBackgroundColor(Color.parseColor("#4403e1"))
        }
        "electric" -> {
            txt.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_electric,
                0,
                0,
                0
            )
            txt.setBackgroundColor(Color.parseColor("#f76b2c"))
        }
        "fairy" -> {
            txt.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_fairy,
                0,
                0,
                0
            )
            txt.setBackgroundColor(Color.parseColor("#c34c87"))
        }
        "fighting" -> {
            txt.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_fighting,
                0,
                0,
                0
            )
            txt.setBackgroundColor(Color.parseColor("#831f1b"))
        }
        "fire" -> {
            txt.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_fire,
                0,
                0,
                0
            )
            txt.setBackgroundColor(Color.parseColor("#c25c10"))
        }
        "ghost" -> {
            txt.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_ghost,
                0,
                0,
                0
            )
            txt.setBackgroundColor(Color.parseColor("#4e3b66"))
        }
        "ground" -> {
            txt.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_ground,
                0,
                0,
                0
            )
            txt.setBackgroundColor(Color.parseColor("#d3a328"))
        }
        "ice" -> {
            txt.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_ice,
                0,
                0,
                0
            )
            txt.setBackgroundColor(Color.parseColor("#5ec5c0"))
        }
        "psychic" -> {
            txt.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_psychic,
                0,
                0,
                0
            )
            txt.setBackgroundColor(Color.parseColor("#f60b53"))
        }
        "rock" -> {
            txt.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_rock,
                0,
                0,
                0
            )
            txt.setBackgroundColor(Color.parseColor("#7b6d24"))
        }
        "steel" -> {
            txt.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_steel,
                0,
                0,
                0
            )
            txt.setBackgroundColor(Color.parseColor("#8989af"))
        }
        "water" -> {
            txt.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_water,
                0,
                0,
                0
            )
            txt.setBackgroundColor(Color.parseColor("#1d5ee9"))
        }

    }
}

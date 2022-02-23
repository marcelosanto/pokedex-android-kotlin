package com.marcelo.pokedex_android_kotlin.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.marcelo.pokedex_android_kotlin.R
import com.marcelo.pokedex_android_kotlin.api.model.PokemonModel
import com.marcelo.pokedex_android_kotlin.databinding.FragmentPokemonAboutBinding
import java.util.*


class PokemonAboutFragment : Fragment() {

    private var _binding: FragmentPokemonAboutBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonAboutBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        val pokemon = bundle!!.getParcelable<PokemonModel>("message")

        val list = weaknessPokemon(pokemon!!.types[0].name)

        for (item in list.indices) {

            if (item == 0) {
                binding.layoutWeakness0.visibility = View.VISIBLE
                setIconAndColorForTextView(list[item], binding.txtWeakness0, binding.cardWeakness0)
            }

            if (item == 1) {
                binding.layoutWeakness1.visibility = View.VISIBLE
                setIconAndColorForTextView(list[item], binding.txtWeakness1, binding.cardWeakness1)
            }

            if (item == 2) {
                binding.layoutWeakness2.visibility = View.VISIBLE
                setIconAndColorForTextView(list[item], binding.txtWeakness2, binding.cardWeakness2)
            }

            if (item == 3) {
                binding.layoutWeakness3.visibility = View.VISIBLE
                setIconAndColorForTextView(list[item], binding.txtWeakness3, binding.cardWeakness3)
            }

            if (item == 4) {
                binding.layoutWeakness4.visibility = View.VISIBLE
                setIconAndColorForTextView(list[item], binding.txtWeakness4, binding.cardWeakness4)
            }


        }

        pokemon.let {

            binding.txtBio.text = it.biography.replace("\n", " ")
            binding.txtSpecies.text = captalizerText(it.species)

            binding.txtAbilities.text = when (it.abilities.size) {
                1 -> captalizerText(it.abilities[0].name)
                2 -> "${captalizerText(it.abilities[0].name)}, ${
                    captalizerText(
                        it.abilities[1].name
                    )
                }"
                else -> ""
            }


            binding.txtBaseExp.text = it.base_experience

            if (it.height.toDouble() / 10 >= 1) {
                binding.txtHeight.text = "${it.height.toDouble() / 10} m"
            } else {
                binding.txtHeight.text = "${it.height.toDouble() / 10} cm"
            }
            binding.txtWeight.text = "${it.weight.toDouble() / 10} Kg"

            binding.txtCatchRate.text = it.capture_rate
            binding.txtBaseFriend.text = it.base_happiness
            binding.txtGrowthRate.text = it.growth_rate.name
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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


    "water" -> listOf("grass", "electric", "fire")


    "grass" -> listOf("fire", "ice", "poison", "flying", "bug")


    "electric" -> listOf("ground", "water")


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

fun setIconAndColorForTextView(
    type: String,
    txt: ImageView,
    card: CardView?
) {
    when (type) {
        "flying" -> {
            txt.setImageResource(R.drawable.ic_flying)
            card?.setCardBackgroundColor(Color.parseColor("#a385e0"))
        }
        "grass" -> {
            txt.setImageResource(
                R.drawable.ic_grass
            )
            card?.setCardBackgroundColor(Color.parseColor("#56972f"))
        }
        "bug" -> {
            txt.setImageResource(
                R.drawable.ic_bug
            )

            card?.setCardBackgroundColor(Color.parseColor("#6a7611"))

        }
        "poison" -> {
            txt.setImageResource(
                R.drawable.ic_poison
            )
            card?.setCardBackgroundColor(Color.parseColor("#6c296a"))
        }
        "normal" -> {
            txt.setImageResource(
                R.drawable.ic_normal
            )
            card?.setCardBackgroundColor(Color.parseColor("#818054"))
        }

        "dark" -> {
            txt.setImageResource(
                R.drawable.ic_dark
            )
            card?.setCardBackgroundColor(Color.parseColor("#413229"))
        }
        "dragon" -> {
            txt.setImageResource(
                R.drawable.ic_dragon
            )
            card?.setCardBackgroundColor(Color.parseColor("#4403e1"))
        }
        "electric" -> {
            txt.setImageResource(
                R.drawable.ic_electric
            )
            card?.setCardBackgroundColor(Color.parseColor("#f76b2c"))
        }
        "fairy" -> {
            txt.setImageResource(
                R.drawable.ic_fairy
            )
            card?.setCardBackgroundColor(Color.parseColor("#c34c87"))
        }
        "fighting" -> {
            txt.setImageResource(
                R.drawable.ic_fighting
            )
            card?.setCardBackgroundColor(Color.parseColor("#831f1b"))
        }
        "fire" -> {
            txt.setImageResource(
                R.drawable.ic_fire
            )
            card?.setCardBackgroundColor(Color.parseColor("#c25c10"))
        }
        "ghost" -> {
            txt.setImageResource(
                R.drawable.ic_ghost
            )
            card?.setCardBackgroundColor(Color.parseColor("#4e3b66"))
        }
        "ground" -> {
            txt.setImageResource(
                R.drawable.ic_ground
            )
            card?.setCardBackgroundColor(Color.parseColor("#d3a328"))
        }
        "ice" -> {
            txt.setImageResource(
                R.drawable.ic_ice
            )
            card?.setCardBackgroundColor(Color.parseColor("#5ec5c0"))
        }
        "psychic" -> {
            txt.setImageResource(
                R.drawable.ic_psychic
            )
            card?.setCardBackgroundColor(Color.parseColor("#f60b53"))
        }
        "rock" -> {
            txt.setImageResource(
                R.drawable.ic_rock
            )
            card?.setCardBackgroundColor(Color.parseColor("#7b6d24"))
        }
        "steel" -> {
            txt.setImageResource(
                R.drawable.ic_steel
            )

            card?.setCardBackgroundColor(Color.parseColor("#8989af"))
        }
        "water" -> {
            txt.setImageResource(
                R.drawable.ic_water
            )

            card?.setCardBackgroundColor(Color.parseColor("#1d5ee9"))
        }

    }
}
package com.marcelo.pokedex_android_kotlin.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.marcelo.pokedex_android_kotlin.databinding.FragmentPokemonAboutBinding
import com.marcelo.pokedex_android_kotlin.domain.Pokemon
import com.marcelo.pokedex_android_kotlin.utils.Const.setIconAndColorForTextView
import com.marcelo.pokedex_android_kotlin.utils.Const.weaknessPokemon
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

    //mudar pra um rv com adapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        val pokemon = bundle!!.getSerializable("message") as Pokemon

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


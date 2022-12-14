package com.marcelo.pokedex_android_kotlin.pokedex.presentation.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.marcelo.pokedex_android_kotlin.api.model.PokemonModel
import com.marcelo.pokedex_android_kotlin.databinding.FragmentEvolutionBinding
import com.marcelo.pokedex_android_kotlin.view.formattedNumber


class EvolutionFragment : Fragment() {

    private var _binding: FragmentEvolutionBinding? = null

    private val binding get() = _binding!!

    val URL =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        _binding = FragmentEvolutionBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bundle = arguments
        val pokemon = bundle!!.getParcelable<PokemonModel>("message")

        changeColorForText(pokemon?.types?.get(0)?.name.toString(), binding.txtEvolution)

        if (pokemon?.evolutions?.chain?.evolves_to?.isEmpty() == true) {
            val pokemonName = pokemon.evolutions.chain.species?.name
            val id = pokemon.evolutions.chain.species?.url?.replace(
                "https://pokeapi.co/api/v2/pokemon-species/",
                ""
            )?.replace("/", "")

            binding.layoutPokemonNotEvolution.visibility = View.VISIBLE

            binding.txtIdPokemonNotEvolution.text = "#${formattedNumber(id.toString())}"
            binding.txtNamePokemonNotEvolution.text = captalizerText(pokemonName.toString())
            Glide.with(this).load("$URL/$id.png").into(binding.imgPokemonNotEvolution)

            Log.w(
                "EVOU",
                "onViewCreated: ${pokemon.evolutions.chain.species?.url}"
            )
        }

        try {
            //primeiro pokemon
            val pokemonName = pokemon?.evolutions?.chain?.species?.name
            val id = pokemon?.evolutions?.chain?.species?.url?.replace(
                "https://pokeapi.co/api/v2/pokemon-species/",
                ""
            )?.replace("/", "")

            if (pokemon?.evolutions?.chain?.evolves_to?.get(0)?.species != null) {

                var secondPokemon = pokemon.evolutions.chain.evolves_to[0].species?.name


                val evoluMinLevel =
                    pokemon.evolutions.chain.evolves_to[0].evolution_details?.get(0)?.min_level

                val secondPokemonId =
                    pokemon.evolutions.chain.evolves_to[0].species?.url?.replace(
                        "https://pokeapi.co/api/v2/pokemon-species/",
                        ""
                    )?.replace("/", "")

                binding.layoutPokemonNotEvolution.visibility = View.GONE
                binding.firstLayout.visibility = View.VISIBLE

                binding.txtIdEvoOne.text = "#${formattedNumber(id.toString())}"
                binding.txtNameEvoOne.text = captalizerText(pokemonName.toString())
                Glide.with(this)
                    .load("$URL/$id.png")
                    .into(binding.imgEvoOne)

                binding.txtIdEvoTwo.text = "#${formattedNumber(secondPokemonId.toString())}"
                binding.txtNameEvoTwo.text = captalizerText(secondPokemon.toString())
                Glide.with(this).load("$URL/$secondPokemonId.png").into(binding.imgEvoTwo)

                binding.txtEvoOneMinLevel.text = "(Level $evoluMinLevel)"
            }

        } catch (e: IndexOutOfBoundsException) {
            Log.e("EVO", "error: ${e.message.toString()}")
        }

        try {
            val secondPokemon = pokemon?.evolutions?.chain?.evolves_to?.get(0)?.species?.name

            val secondPokemonId =
                pokemon?.evolutions?.chain?.evolves_to?.get(0)?.species?.url?.replace(
                    "https://pokeapi.co/api/v2/pokemon-species/",
                    ""
                )?.replace("/", "")

            if (pokemon?.evolutions?.chain?.evolves_to?.get(0)?.evolves_to?.get(0)?.species != null) {

                val thirdPokemon =
                    pokemon.evolutions.chain.evolves_to[0].evolves_to?.get(0)?.species?.name


                val thirdEvoMinLeve =
                    pokemon.evolutions.chain.evolves_to[0].evolves_to?.get(0)?.evolution_details?.get(
                        0
                    )?.min_level

                val thirdPokemonId =
                    pokemon.evolutions.chain.evolves_to[0].evolves_to?.get(0)?.species?.url?.replace(
                        "https://pokeapi.co/api/v2/pokemon-species/",
                        ""
                    )?.replace("/", "")

                binding.layoutPokemonNotEvolution.visibility = View.GONE
                binding.layoutThird.visibility = View.VISIBLE

                binding.txtIdEvoTwos.text = "#${formattedNumber(secondPokemonId.toString())}"
                binding.txtNameEvoTwos.text = captalizerText(secondPokemon.toString())
                Glide.with(this).load("$URL/$secondPokemonId.png").into(binding.imgEvoTwos)

                binding.txtEvoThirdMinLevel.text = "(Level $thirdEvoMinLeve)"

                binding.txtIdEvoThird.text = "#${formattedNumber(thirdPokemonId.toString())}"
                binding.txtNameEvoThird.text = captalizerText(thirdPokemon.toString())
                Glide.with(this).load("$URL/$thirdPokemonId.png").into(binding.imgEvoThird)
            }

        } catch (e: IndexOutOfBoundsException) {
            Log.e("EVO", "error: ${e.message.toString()}")
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


private fun changeColorForText(
    type: String,
    txt: TextView,
) {
    when (type) {
        "grass" -> {
            txt.setTextColor(Color.parseColor("#56972f"))
        }
        "bug" -> {
            txt.setTextColor(Color.parseColor("#6a7611"))
        }
        "poison" -> {
            txt.setTextColor(Color.parseColor("#6c296a"))
        }
        "normal" -> {
            txt.setTextColor(Color.parseColor("#818054"))
        }

        "dark" -> {
            txt.setTextColor(Color.parseColor("#413229"))
        }
        "dragon" -> {
            txt.setTextColor(Color.parseColor("#4403e1"))
        }
        "electric" -> {
            txt.setTextColor(Color.parseColor("#f76b2c"))
        }
        "fairy" -> {
            txt.setTextColor(Color.parseColor("#c34c87"))
        }
        "fighting" -> {
            txt.setTextColor(Color.parseColor("#831f1b"))
        }
        "fire" -> {
            txt.setTextColor(Color.parseColor("#c25c10"))
        }
        "ghost" -> {
            txt.setTextColor(Color.parseColor("#4e3b66"))
        }
        "ground" -> {
            txt.setTextColor(Color.parseColor("#d3a328"))
        }
        "ice" -> {
            txt.setTextColor(Color.parseColor("#5ec5c0"))
        }
        "psychic" -> {
            txt.setTextColor(Color.parseColor("#f60b53"))
        }
        "rock" -> {
            txt.setTextColor(Color.parseColor("#7b6d24"))
        }
        "steel" -> {
            txt.setTextColor(Color.parseColor("#8989af"))
        }
        "water" -> {
            txt.setTextColor(Color.parseColor("#1d5ee9"))
        }

    }
}
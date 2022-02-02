package com.marcelo.pokedex_android_kotlin.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.marcelo.pokedex_android_kotlin.R
import com.marcelo.pokedex_android_kotlin.api.model.PokemonModel


class EvolutionFragment : Fragment() {
    val URL =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_evolution, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val evoOneId: TextView = view.findViewById(R.id.txtIdEvoOne)

        val evoOneName = view.findViewById<TextView>(R.id.txtNameEvoOne)
        val evoOneImage = view.findViewById<ImageView>(R.id.imgEvoOne)

        val EvoOneMinLevel = view.findViewById<TextView>(R.id.txtEvoOneMinLevel)

        val evoTwoId = view.findViewById<TextView>(R.id.txtIdEvoTwo)
        val evoTwoName = view.findViewById<TextView>(R.id.txtNameEvoTwo)
        val evoTwoImage = view.findViewById<ImageView>(R.id.imgEvoTwo)

        val evoTwosId = view.findViewById<TextView>(R.id.txtIdEvoTwos)
        val evoTwosName = view.findViewById<TextView>(R.id.txtNameEvoTwos)
        val evoTwosImage = view.findViewById<ImageView>(R.id.imgEvoTwos)


        val EvoThirdMinLevel = view.findViewById<TextView>(R.id.txtEvoThirdMinLevel)

        val evoThirdId = view.findViewById<TextView>(R.id.txtIdEvoThird)
        val evoThirdName = view.findViewById<TextView>(R.id.txtNameEvoThird)
        val evoThirdImage = view.findViewById<ImageView>(R.id.imgEvoThird)

        val pokemonNotEvolutionId = view.findViewById<TextView>(R.id.txtIdPokemonNotEvolution)
        val pokemonNotEvolutionName = view.findViewById<TextView>(R.id.txtNamePokemonNotEvolution)
        val pokemonNotEvolutionImage = view.findViewById<ImageView>(R.id.imgPokemonNotEvolution)

        val txtEvolution = view.findViewById<TextView>(R.id.txtEvolution)

        val layoutThird = view.findViewById<LinearLayout>(R.id.layoutThird)
        val layoutFirst = view.findViewById<LinearLayout>(R.id.firstLayout)
        val layoutPokemonNotEvolution =
            view.findViewById<LinearLayout>(R.id.layoutPokemonNotEvolution)

        val bundle = arguments
        val pokemon = bundle!!.getParcelable<PokemonModel>("message")

        changeColorForText(pokemon?.types?.get(0)?.name.toString(), txtEvolution)

        if (pokemon?.evolutions?.chain?.evolves_to?.isEmpty() == true) {
            val pokemonName = pokemon?.evolutions?.chain.species?.name
            val id = pokemon?.evolutions?.chain.species?.url?.replace(
                "https://pokeapi.co/api/v2/pokemon-species/",
                ""
            )?.replace("/", "")

            layoutPokemonNotEvolution.visibility = View.VISIBLE

            pokemonNotEvolutionId.text = "#${formattedNumber(id.toString())}"
            pokemonNotEvolutionName.text = captalizerText(pokemonName.toString())
            Glide.with(this).load("$URL/$id.png").into(pokemonNotEvolutionImage!!)

            Log.w(
                "EVOU",
                "onViewCreated: ${pokemon?.evolutions?.chain.species?.url}"
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

                layoutPokemonNotEvolution.visibility = View.GONE
                layoutFirst.visibility = View.VISIBLE

                evoOneId.text = "#${formattedNumber(id.toString())}"
                evoOneName.text = captalizerText(pokemonName.toString())
                Glide.with(this)
                    .load("$URL/$id.png")
                    .into(evoOneImage!!)

                evoTwoId.text = "#${formattedNumber(secondPokemonId.toString())}"
                evoTwoName.text = captalizerText(secondPokemon.toString())
                Glide.with(this).load("$URL/$secondPokemonId.png").into(evoTwoImage!!)

                EvoOneMinLevel?.text = "(Level $evoluMinLevel)"
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

                layoutPokemonNotEvolution.visibility = View.GONE
                layoutThird.visibility = View.VISIBLE

                evoTwosId.text = "#${formattedNumber(secondPokemonId.toString())}"
                evoTwosName.text = captalizerText(secondPokemon.toString())
                Glide.with(this).load("$URL/$secondPokemonId.png").into(evoTwosImage!!)

                EvoThirdMinLevel.text = "(Level $thirdEvoMinLeve)"

                evoThirdId.text = "#${formattedNumber(thirdPokemonId.toString())}"
                evoThirdName.text = captalizerText(thirdPokemon.toString())
                Glide.with(this).load("$URL/$thirdPokemonId.png").into(evoThirdImage!!)
            }

        } catch (e: IndexOutOfBoundsException) {
            Log.e("EVO", "error: ${e.message.toString()}")
        }

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
package com.marcelo.pokedex_android_kotlin.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.marcelo.pokedex_android_kotlin.R
import com.marcelo.pokedex_android_kotlin.api.model.PokemonModel


class EvolutionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_evolution, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val URL =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"

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

        val bundle = arguments
        val pokemon = bundle!!.getParcelable<PokemonModel>("message")

        //primeiro pokemon
        val pokemonName = pokemon?.evolutions?.chain?.species?.name
        val id = pokemon?.evolutions?.chain?.species?.url?.replace(
            "https://pokeapi.co/api/v2/pokemon-species/",
            ""
        )?.replace("/", "")

        try {
            val secondPokemon = pokemon?.evolutions?.chain?.evolves_to?.get(0)?.species?.name

            val evoluMinLevel =
                pokemon?.evolutions?.chain?.evolves_to?.get(0)?.evolution_details?.get(0)?.min_level

            val secondPokemonId =
                pokemon?.evolutions?.chain?.evolves_to?.get(0)?.species?.url?.replace(
                    "https://pokeapi.co/api/v2/pokemon-species/",
                    ""
                )?.replace("/", "")


            evoOneId.text = "#${formattedNumber(id.toString())}"
            evoOneName.text = captalizerText(pokemonName.toString())
            Glide.with(this)
                .load("$URL/$id.png")
                .into(evoOneImage!!)


            evoTwoId.text = "#${formattedNumber(secondPokemonId.toString())}"
            evoTwoName.text = captalizerText(secondPokemon.toString())
            Glide.with(this).load("$URL/$secondPokemonId.png").into(evoTwoImage!!)

            EvoOneMinLevel?.text = "(Level $evoluMinLevel)"


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

            val thirdPokemon =
                pokemon?.evolutions?.chain?.evolves_to?.get(0)?.evolves_to?.get(0)?.species?.name


            val thirdEvoMinLeve =
                pokemon?.evolutions?.chain?.evolves_to?.get(0)?.evolves_to?.get(0)?.evolution_details?.get(
                    0
                )?.min_level

            val thirdPokemonId =
                pokemon?.evolutions?.chain?.evolves_to?.get(0)?.evolves_to?.get(0)?.species?.url?.replace(
                    "https://pokeapi.co/api/v2/pokemon-species/",
                    ""
                )?.replace("/", "")


            evoTwosId.text = "#${formattedNumber(secondPokemonId.toString())}"
            evoTwosName.text = captalizerText(secondPokemon.toString())
            Glide.with(this).load("$URL/$secondPokemonId.png").into(evoTwosImage!!)

            EvoThirdMinLevel.text = "(Level $thirdEvoMinLeve)"

            evoThirdId.text = "#${formattedNumber(thirdPokemonId.toString())}"
            evoThirdName.text = captalizerText(thirdPokemon.toString())
            Glide.with(this).load("$URL/$thirdPokemonId.png").into(evoThirdImage!!)


        } catch (e: IndexOutOfBoundsException) {
            Log.e("EVO", "error: ${e.message.toString()}")
        }


    }


}
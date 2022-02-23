package com.marcelo.pokedex_android_kotlin.view

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.marcelo.pokedex_android_kotlin.api.model.PokemonModel
import com.marcelo.pokedex_android_kotlin.databinding.FragmentStatsBinding

class StatsFragment : Fragment() {

    private var _binding: FragmentStatsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        val pokemon = bundle!!.getParcelable<PokemonModel>("message")

        val hp = pokemon?.stats?.get(0)?.base_stat
        val attack = pokemon?.stats?.get(1)?.base_stat
        val defense = pokemon?.stats?.get(2)?.base_stat
        val specialAttack = pokemon?.stats?.get(3)?.base_stat
        val specialDefense = pokemon?.stats?.get(4)?.base_stat
        val speed = pokemon?.stats?.get(5)?.base_stat

        val typeStatsDefense = pokemon?.types?.get(0)?.name?.let { typeDefensesPokemon(it) }

        binding.progressBar.max = 200
        binding.progressBarAttack.max = 200
        binding.progressBarDefense.max = 200
        binding.progressBarSpAtk.max = 200
        binding.progressBarSpDef.max = 200
        binding.progressBarSpeed.max = 200

        if (hp != null && attack != null && defense != null && specialAttack != null && specialDefense != null && speed != null) {
            setProgressBarXp(binding.progressBar, hp)
            binding.txtxHp.text = hp.toString()

            setProgressBarXp(binding.progressBarAttack, attack)
            binding.txtAttak.text = attack.toString()

            setProgressBarXp(binding.progressBarDefense, defense)
            binding.txtDef.text = defense.toString()

            setProgressBarXp(binding.progressBarSpAtk, specialAttack)
            binding.txtSpAtk.text = specialAttack.toString()

            setProgressBarXp(binding.progressBarSpDef, specialDefense)
            binding.txtSpDef.text = specialDefense.toString()

            setProgressBarXp(binding.progressBarSpeed, speed)
            binding.txtSpeed.text = speed.toString()

            var total = hp + attack + defense + specialAttack + specialDefense + speed

            binding.txtTotal.text = total.toString()
        }

        binding.txtTypeEffect.text =
            "The effectiveness of each type on ${pokemon?.name?.let { captalizerText(it) }}."


        val txtArray = listOf<TextView>(
            binding.txtType1,
            binding.txtType2,
            binding.txtType3,
            binding.txtType4,
            binding.txtType5,
            binding.txtType6,
            binding.txtType7,
            binding.txtType8,
            binding.txtType9,
            binding.txtType10,
            binding.txtType11,
            binding.txtType12,
            binding.txtType13,
            binding.txtType14,
            binding.txtType15,
            binding.txtType16,
            binding.txtType17,
            binding.txtType18
        )

        if (typeStatsDefense != null) {
            for (type in typeStatsDefense.indices) {
                Log.i("TYPE", "TYPE: $type")
                txtArray[type].text = typeStatsDefense[type]
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

fun setProgressBarXp(pgr: ProgressBar, currentProgress: Int) {
    ObjectAnimator.ofInt(pgr, "progress", currentProgress).setDuration(2000).start()
}

fun typeDefensesPokemon(type: String): List<String> = when (type) {
    "normal" -> listOf(
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "½",
        "N",
        "0",
        "0",
        "½",
        "0"
    )

    "fire" -> listOf(
        "0",
        "½",
        "½",
        "0",
        "2",
        "2",
        "0",
        "0",
        "0",
        "0",
        "0",
        "2",
        "½",
        "0",
        "½",
        "0",
        "2",
        "0"
    )


    "water" -> listOf(
        "0",
        "2",
        "½",
        "0",
        "½",
        "0",
        "0",
        "0",
        "2",
        "0",
        "0",
        "0",
        "2",
        "0",
        "½",
        "0",
        "0",
        "0"
    )

    "electric" -> listOf(
        "0",
        "0",
        "2",
        "½",
        "½",
        "0",
        "0",
        "0",
        "N",
        "2",
        "0",
        "0",
        "0",
        "0",
        "½",
        "0",
        "0",
        "0"
    )

    "grass" -> listOf(
        "0",
        "½",
        "2",
        "0",
        "½",
        "0",
        "0",
        "½",
        "2",
        "½",
        "0",
        "½",
        "2",
        "0",
        "½",
        "0",
        "½",
        "0"
    )

    "ice" -> listOf(
        "0",
        "½",
        "½",
        "0",
        "2",
        "½",
        "0",
        "0",
        "2",
        "2",
        "0",
        "0",
        "0",
        "0",
        "2",
        "0",
        "½",
        "0"
    )


    "fighting" -> listOf(
        "2",
        "0",
        "0",
        "0",
        "0",
        "2",
        "0",
        "½",
        "0",
        "½",
        "½",
        "½",
        "2",
        "N",
        "0",
        "2",
        "2",
        "½"
    )


    "poison" -> listOf(
        "0",
        "0",
        "0",
        "0",
        "2",
        "0",
        "0",
        "½",
        "½",
        "0",
        "0",
        "0",
        "½",
        "½",
        "0",
        "0",
        "N",
        "2"
    )


    "ground" -> listOf(
        "0",
        "2",
        "0",
        "2",
        "½",
        "0",
        "0",
        "2",
        "0",
        "N",
        "0",
        "½",
        "2",
        "0",
        "0",
        "0",
        "2",
        "0"
    )


    "flying" -> listOf(
        "0",
        "0",
        "0",
        "½",
        "2",
        "0",
        "2",
        "0",
        "0",
        "0",
        "0",
        "2",
        "½",
        "0",
        "0",
        "0",
        "½",
        "0"
    )


    "psychic" -> listOf(
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "2",
        "2",
        "0",
        "0",
        "½",
        "0",
        "0",
        "0",
        "0",
        "N",
        "½",
        "0"
    )


    "bug" -> listOf(
        "0",
        "½",
        "0",
        "0",
        "2",
        "0",
        "½",
        "½",
        "0",
        "½",
        "2",
        "0",
        "0",
        "½",
        "0",
        "2",
        "½",
        "½"
    )


    "rock" -> listOf(
        "0",
        "2",
        "0",
        "0",
        "0",
        "2",
        "½",
        "0",
        "½",
        "2",
        "0",
        "2",
        "0",
        "0",
        "0",
        "0",
        "½",
        "0"
    )


    "ghost" -> listOf(
        "N",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "2",
        "0",
        "0",
        "2",
        "0",
        "½",
        "0",
        "0"
    )


    "dragon" -> listOf(
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "2",
        "0",
        "½",
        "N"
    )


    "dark" -> listOf(
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "½",
        "0",
        "0",
        "0",
        "2",
        "0",
        "0",
        "2",
        "0",
        "½",
        "0",
        "½",
    )


    "steel" -> listOf(
        "0",
        "½",
        "½",
        "½",
        "0",
        "2",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "2",
        "0",
        "0",
        "0",
        "½",
        "2"
    )


    "fairy" -> listOf(
        "0",
        "½",
        "0",
        "0",
        "0",
        "0",
        "2",
        "½",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "2",
        "2",
        "½",
        "0"
    )


    else -> listOf("deu ruim")
}
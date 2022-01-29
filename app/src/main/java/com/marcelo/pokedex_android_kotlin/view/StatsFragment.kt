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
import com.marcelo.pokedex_android_kotlin.R
import com.marcelo.pokedex_android_kotlin.api.model.PokemonModel

class StatsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false)
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

        val progressBarHP: ProgressBar = view.findViewById(R.id.progressBar)
        val progressBarAttack: ProgressBar = view.findViewById(R.id.progressBarAttack)
        val progressBarDefense: ProgressBar = view.findViewById(R.id.progressBarDefense)
        val progressBarSpAttk: ProgressBar = view.findViewById(R.id.progressBarSpAtk)
        val progressBarSpDef: ProgressBar = view.findViewById(R.id.progressBarSpDef)
        val progressBarSpeed: ProgressBar = view.findViewById(R.id.progressBarSpeed)


        val txtHP: TextView = view.findViewById(R.id.txtxHp)
        val txtAttak: TextView = view.findViewById(R.id.txtAttak)
        val txtDef: TextView = view.findViewById(R.id.txtDef)
        val txtSpAtk: TextView = view.findViewById(R.id.txtSpAtk)
        val txtSpDef: TextView = view.findViewById(R.id.txtSpDef)
        val txtSpeed: TextView = view.findViewById(R.id.txtSpeed)
        val txtTotal: TextView = view.findViewById(R.id.txtTotal)
        val txtTypeEffect: TextView = view.findViewById(R.id.txtTypeEffect)


        progressBarHP.max = 200
        progressBarAttack.max = 200
        progressBarDefense.max = 200
        progressBarSpAttk.max = 200
        progressBarSpDef.max = 200
        progressBarSpeed.max = 200

        if (hp != null && attack != null && defense != null && specialAttack != null && specialDefense != null && speed != null) {
            setProgressBarXp(progressBarHP, hp)
            txtHP.text = hp.toString()

            setProgressBarXp(progressBarAttack, attack)
            txtAttak.text = attack.toString()

            setProgressBarXp(progressBarDefense, defense)
            txtDef.text = defense.toString()

            setProgressBarXp(progressBarSpAttk, specialAttack)
            txtSpAtk.text = specialAttack.toString()

            setProgressBarXp(progressBarSpDef, specialDefense)
            txtSpDef.text = specialDefense.toString()

            setProgressBarXp(progressBarSpeed, speed)
            txtSpeed.text = speed.toString()

            var total = hp + attack + defense + specialAttack + specialDefense + speed

            txtTotal.text = total.toString()
        }

        txtTypeEffect.text =
            "The effectiveness of each type on ${pokemon?.name?.let { captalizerText(it) }}."

        val txtType1: TextView = view.findViewById(R.id.txtType1)
        val txtType2: TextView = view.findViewById(R.id.txtType2)
        val txtType3: TextView = view.findViewById(R.id.txtType3)
        val txtType4: TextView = view.findViewById(R.id.txtType4)
        val txtType5: TextView = view.findViewById(R.id.txtType5)
        val txtType6: TextView = view.findViewById(R.id.txtType6)
        val txtType7: TextView = view.findViewById(R.id.txtType7)
        val txtType8: TextView = view.findViewById(R.id.txtType8)
        val txtType9: TextView = view.findViewById(R.id.txtType9)
        val txtType10: TextView = view.findViewById(R.id.txtType10)
        val txtType11: TextView = view.findViewById(R.id.txtType11)
        val txtType12: TextView = view.findViewById(R.id.txtType12)
        val txtType13: TextView = view.findViewById(R.id.txtType13)
        val txtType14: TextView = view.findViewById(R.id.txtType14)
        val txtType15: TextView = view.findViewById(R.id.txtType15)
        val txtType16: TextView = view.findViewById(R.id.txtType16)
        val txtType17: TextView = view.findViewById(R.id.txtType17)
        val txtType18: TextView = view.findViewById(R.id.txtType18)

        val txtArray = listOf<TextView>(
            txtType1,
            txtType2,
            txtType3,
            txtType4,
            txtType5,
            txtType6,
            txtType7,
            txtType8,
            txtType9,
            txtType10,
            txtType11,
            txtType12,
            txtType13,
            txtType14,
            txtType15,
            txtType16,
            txtType17,
            txtType18
        )

        if (typeStatsDefense != null) {
            for (type in typeStatsDefense.indices) {
                Log.i("TYPE", "TYPE: $type")
                txtArray[type].text = typeStatsDefense[type]
            }
        }

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
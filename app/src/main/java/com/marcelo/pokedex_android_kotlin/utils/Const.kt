package com.marcelo.pokedex_android_kotlin.utils

import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.marcelo.pokedex_android_kotlin.R
import com.marcelo.pokedex_android_kotlin.data.model.ModelPokemon

object Const {
    fun changeColorForBackandLabel(
        type: String,
        img: ImageView,
        layout: CardView? = null,
        typeLayout: CardView? = null
    ) {
        when (type) {
            "flying" -> {
                img.setImageResource(R.drawable.ic_flying)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#a385e0"))
                layout?.setCardBackgroundColor(Color.parseColor("#614f86"))

            }
            "grass" -> {
                img.setImageResource(R.drawable.ic_grass)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#56972f"))
                layout?.setCardBackgroundColor(Color.parseColor("#7AC74C"))
            }
            "bug" -> {
                img.setImageResource(R.drawable.ic_bug)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#6a7611"))
                layout?.setCardBackgroundColor(Color.parseColor("#A6B91A"))
            }
            "poison" -> {
                img.setImageResource(R.drawable.ic_poison)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#6c296a"))
                layout?.setCardBackgroundColor(Color.parseColor("#A33EA1"))
            }
            "normal" -> {
                img.setImageResource(R.drawable.ic_normal)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#818054"))
                layout?.setCardBackgroundColor(Color.parseColor("#A8A77A"))
            }

            "dark" -> {
                img.setImageResource(R.drawable.ic_dark)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#413229"))
                layout?.setCardBackgroundColor(Color.parseColor("#705746"))
            }
            "dragon" -> {
                img.setImageResource(R.drawable.ic_dragon)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#4403e1"))
                layout?.setCardBackgroundColor(Color.parseColor("#6F35FC"))
            }
            "electric" -> {
                img.setImageResource(R.drawable.ic_electric)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#f76b2c"))
                layout?.setCardBackgroundColor(Color.parseColor("#F7D02C"))
            }
            "fairy" -> {
                img.setImageResource(R.drawable.ic_fairy)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#c34c87"))
                layout?.setCardBackgroundColor(Color.parseColor("#D685AD"))
            }
            "fighting" -> {
                img.setImageResource(R.drawable.ic_fighting)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#831f1b"))
                layout?.setCardBackgroundColor(Color.parseColor("#C22E28"))
            }
            "fire" -> {
                img.setImageResource(R.drawable.ic_fire)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#c25c10"))
                layout?.setCardBackgroundColor(Color.parseColor("#EE8130"))
            }
            "ghost" -> {
                img.setImageResource(R.drawable.ic_ghost)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#4e3b66"))
                layout?.setCardBackgroundColor(Color.parseColor("#735797"))
            }
            "ground" -> {
                img.setImageResource(R.drawable.ic_ground)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#d3a328"))
                layout?.setCardBackgroundColor(Color.parseColor("#E2BF65"))
            }
            "ice" -> {
                img.setImageResource(R.drawable.ic_ice)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#5ec5c0"))
                layout?.setCardBackgroundColor(Color.parseColor("#96D9D6"))
            }
            "psychic" -> {
                img.setImageResource(R.drawable.ic_psychic)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#f60b53"))
                layout?.setCardBackgroundColor(Color.parseColor("#F95587"))
            }
            "rock" -> {
                img.setImageResource(R.drawable.ic_rock)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#7b6d24"))
                layout?.setCardBackgroundColor(Color.parseColor("#B6A136"))
            }
            "steel" -> {
                img.setImageResource(R.drawable.ic_steel)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#8989af"))
                layout?.setCardBackgroundColor(Color.parseColor("#B7B7CE"))
            }
            "water" -> {
                img.setImageResource(R.drawable.ic_water)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#1d5ee9"))
                layout?.setCardBackgroundColor(Color.parseColor("#6390F0"))
            }

        }
    }

    fun colorType(type: String): String = when (type) {
        "normal" -> "#818054"
        "fire" -> "#c25c10"
        "water" -> "#1d5ee9"
        "electric" -> "#FFEF00"
        "grass" -> "#56972f"
        "ice" -> "#5ec5c0"
        "fighting" -> "#831f1b"
        "poison" -> "#6c296a"
        "ground" -> "#d3a328"
        "flying" -> "#a385e0"
        "psychic" -> "#f60b53"
        "bug" -> "#6a7611"
        "rock" -> "#7b6d24"
        "ghost" -> "#4e3b66"
        "dragon" -> "#4403e1"
        "dark" -> "#413229"
        "steel" -> "#8989af"
        "fairy" -> "#c34c87"
        "tall" -> "#2F4F4F"
        "medium" -> "#6A5ACD"
        "short" -> "#FF1493"
        "light" -> "#90EE90"
        "pnormal" -> "#6495ED"
        "heavy" -> "#778899"
        else -> type
    }

    fun changeColorForBackandLabelConstraint(
        type: String,
        img: ImageView,
        layout: ConstraintLayout? = null,
        typeLayout: CardView? = null
    ) {
        when (type) {
            "flying" -> {
                img.setImageResource(R.drawable.ic_flying)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#a385e0"))
                layout?.setBackgroundColor(Color.parseColor("#614f86"))

            }
            "grass" -> {
                img.setImageResource(R.drawable.ic_grass)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#56972f"))
                layout?.setBackgroundColor(Color.parseColor("#7AC74C"))
            }
            "bug" -> {
                img.setImageResource(R.drawable.ic_bug)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#6a7611"))
                layout?.setBackgroundColor(Color.parseColor("#A6B91A"))
            }
            "poison" -> {
                img.setImageResource(R.drawable.ic_poison)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#6c296a"))
                layout?.setBackgroundColor(Color.parseColor("#A33EA1"))
            }
            "normal" -> {
                img.setImageResource(R.drawable.ic_normal)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#818054"))
                layout?.setBackgroundColor(Color.parseColor("#A8A77A"))
            }

            "dark" -> {
                img.setImageResource(R.drawable.ic_dark)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#413229"))
                layout?.setBackgroundColor(Color.parseColor("#705746"))
            }
            "dragon" -> {
                img.setImageResource(R.drawable.ic_dragon)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#4403e1"))
                layout?.setBackgroundColor(Color.parseColor("#6F35FC"))
            }
            "electric" -> {
                img.setImageResource(R.drawable.ic_electric)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#f76b2c"))
                layout?.setBackgroundColor(Color.parseColor("#F7D02C"))
            }
            "fairy" -> {
                img.setImageResource(R.drawable.ic_fairy)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#c34c87"))
                layout?.setBackgroundColor(Color.parseColor("#D685AD"))
            }
            "fighting" -> {
                img.setImageResource(R.drawable.ic_fighting)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#831f1b"))
                layout?.setBackgroundColor(Color.parseColor("#C22E28"))
            }
            "fire" -> {
                img.setImageResource(R.drawable.ic_fire)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#c25c10"))
                layout?.setBackgroundColor(Color.parseColor("#EE8130"))
            }
            "ghost" -> {
                img.setImageResource(R.drawable.ic_ghost)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#4e3b66"))
                layout?.setBackgroundColor(Color.parseColor("#735797"))
            }
            "ground" -> {
                img.setImageResource(R.drawable.ic_ground)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#d3a328"))
                layout?.setBackgroundColor(Color.parseColor("#E2BF65"))
            }
            "ice" -> {
                img.setImageResource(R.drawable.ic_ice)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#5ec5c0"))
                layout?.setBackgroundColor(Color.parseColor("#96D9D6"))
            }
            "psychic" -> {
                img.setImageResource(R.drawable.ic_psychic)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#f60b53"))
                layout?.setBackgroundColor(Color.parseColor("#F95587"))
            }
            "rock" -> {
                img.setImageResource(R.drawable.ic_rock)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#7b6d24"))
                layout?.setBackgroundColor(Color.parseColor("#B6A136"))
            }
            "steel" -> {
                img.setImageResource(R.drawable.ic_steel)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#8989af"))
                layout?.setBackgroundColor(Color.parseColor("#B7B7CE"))
            }
            "water" -> {
                img.setImageResource(R.drawable.ic_water)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#1d5ee9"))
                layout?.setBackgroundColor(Color.parseColor("#6390F0"))
            }

        }
    }

    fun changeColorForText(
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

    fun findId(url: String) = url.dropLast(1).takeLastWhile { it.isDigit() }.toInt()

    fun orderByNameZandA(filteredList: MutableList<ModelPokemon>) =
        (filteredList.sortedByDescending { it.name }).toMutableList()

    fun orderByNameAandZ(filteredList: MutableList<ModelPokemon>) =
        (filteredList.sortedBy { it.name }).toMutableList()


    fun highNumberPokemonFirst(filteredList: MutableList<ModelPokemon>) =
        (filteredList.sortedByDescending { it.id.toInt() }).toMutableList()


    fun smallNumberPokemonFirst(filteredList: MutableList<ModelPokemon>) =
        (filteredList.sortedBy { it.id.toInt() }).toMutableList()

}
package com.marcelo.pokedex_android_kotlin.utils

import android.graphics.Color
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.marcelo.pokedex_android_kotlin.R

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
}
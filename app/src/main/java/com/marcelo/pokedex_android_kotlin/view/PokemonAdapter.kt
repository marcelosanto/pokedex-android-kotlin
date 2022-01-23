package com.marcelo.pokedex_android_kotlin.view

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcelo.pokedex_android_kotlin.R
import com.marcelo.pokedex_android_kotlin.domain.Pokemon

class PokemonAdapter(
    private val items: List<Pokemon?>
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.bindView(item)
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Pokemon?) = with(itemView) {
            val imgPokemon = findViewById<ImageView>(R.id.imgPokemon)
            val txtId = findViewById<TextView>(R.id.txt_idNumber)
            val txtName = findViewById<TextView>(R.id.txt_pokeName)
            val txtType01 = findViewById<TextView>(R.id.txt_type01)
            val txtType02 = findViewById<TextView>(R.id.txt_type02)
            val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout);



            item?.let {
                Glide.with(itemView.context).load(it.imageUrl).into(imgPokemon)

                txtId.text = "#${item.formattedNumber}"
                txtName.text = item.name
                txtType01.text = item.types[0].name.capitalize()

                when (item.types[0].name) {
                    "grass" -> {
                        txtType01.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_grass,
                            0,
                            0,
                            0
                        )
                        txtType01.setBackgroundColor(Color.parseColor("#56972f"))
                        constraintLayout.setBackgroundColor(Color.parseColor("#7AC74C"))
                    }
                    "bug" -> {
                        txtType01.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_bug,
                            0,
                            0,
                            0
                        )
                        txtType01.setBackgroundColor(Color.parseColor("#6a7611"))
                        constraintLayout.setBackgroundColor(Color.parseColor("#A6B91A"))
                    }
                    "poison" -> {
                        txtType01.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_poison,
                            0,
                            0,
                            0
                        )
                        txtType01.setBackgroundColor(Color.parseColor("#6c296a"))
                        constraintLayout.setBackgroundColor(Color.parseColor("#A33EA1"))
                    }
                    "normal" -> {
                        txtType01.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_normal,
                            0,
                            0,
                            0
                        )
                        txtType01.setBackgroundColor(Color.parseColor("#818054"))
                        constraintLayout.setBackgroundColor(Color.parseColor("#A8A77A"))
                    }

                    "dark" -> {
                        txtType01.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_dark,
                            0,
                            0,
                            0
                        )
                        txtType01.setBackgroundColor(Color.parseColor("#413229"))
                        constraintLayout.setBackgroundColor(Color.parseColor("#705746"))
                    }
                    "dragon" -> {
                        txtType01.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_dragon,
                            0,
                            0,
                            0
                        )
                        txtType01.setBackgroundColor(Color.parseColor("#4403e1"))
                        constraintLayout.setBackgroundColor(Color.parseColor("#6F35FC"))
                    }
                    "electric" -> {
                        txtType01.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_electric,
                            0,
                            0,
                            0
                        )
                        txtType01.setBackgroundColor(Color.parseColor("#f76b2c"))
                        constraintLayout.setBackgroundColor(Color.parseColor("#F7D02C"))
                    }
                    "fairy" -> {
                        txtType01.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_fairy,
                            0,
                            0,
                            0
                        )
                        txtType01.setBackgroundColor(Color.parseColor("#c34c87"))
                        constraintLayout.setBackgroundColor(Color.parseColor("#D685AD"))
                    }
                    "fighting" -> {
                        txtType01.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_fighting,
                            0,
                            0,
                            0
                        )
                        txtType01.setBackgroundColor(Color.parseColor("#831f1b"))
                        constraintLayout.setBackgroundColor(Color.parseColor("#C22E28"))
                    }
                    "fire" -> {
                        txtType01.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_fire,
                            0,
                            0,
                            0
                        )
                        txtType01.setBackgroundColor(Color.parseColor("#c25c10"))
                        constraintLayout.setBackgroundColor(Color.parseColor("#EE8130"))
                    }
                    "ghost" -> {
                        txtType01.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_ghost,
                            0,
                            0,
                            0
                        )
                        txtType01.setBackgroundColor(Color.parseColor("#4e3b66"))
                        constraintLayout.setBackgroundColor(Color.parseColor("#735797"))
                    }
                    "ground" -> {
                        txtType01.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_ground,
                            0,
                            0,
                            0
                        )
                        txtType01.setBackgroundColor(Color.parseColor("#d3a328"))
                        constraintLayout.setBackgroundColor(Color.parseColor("#E2BF65"))
                    }
                    "ice" -> {
                        txtType01.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_ice,
                            0,
                            0,
                            0
                        )
                        txtType01.setBackgroundColor(Color.parseColor("#5ec5c0"))
                        constraintLayout.setBackgroundColor(Color.parseColor("#96D9D6"))
                    }
                    "psychic" -> {
                        txtType01.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_psychic,
                            0,
                            0,
                            0
                        )
                        txtType01.setBackgroundColor(Color.parseColor("#f60b53"))
                        constraintLayout.setBackgroundColor(Color.parseColor("#F95587"))
                    }
                    "rock" -> {
                        txtType01.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_rock,
                            0,
                            0,
                            0
                        )
                        txtType01.setBackgroundColor(Color.parseColor("#7b6d24"))
                        constraintLayout.setBackgroundColor(Color.parseColor("#B6A136"))
                    }
                    "steel" -> {
                        txtType01.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_steel,
                            0,
                            0,
                            0
                        )
                        txtType01.setBackgroundColor(Color.parseColor("#8989af"))
                        constraintLayout.setBackgroundColor(Color.parseColor("#B7B7CE"))
                    }
                    "water" -> {
                        txtType01.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_water,
                            0,
                            0,
                            0
                        )
                        txtType01.setBackgroundColor(Color.parseColor("#1d5ee9"))
                        constraintLayout.setBackgroundColor(Color.parseColor("#6390F0"))
                    }

                }

                if (item.types.size > 1) {
                    txtType02.visibility = View.VISIBLE
                    txtType02.text = item.types[1].name.capitalize()

                    when (item.types[1].name) {
                        "grass" -> {
                            txtType02.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_grass,
                                0,
                                0,
                                0
                            )
                            txtType02.setBackgroundColor(Color.parseColor("#56972f"))
                        }
                        "bug" -> {
                            txtType02.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_bug,
                                0,
                                0,
                                0
                            )
                            txtType02.setBackgroundColor(Color.parseColor("#6a7611"))
                        }
                        "poison" -> {
                            txtType02.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_poison,
                                0,
                                0,
                                0
                            )
                            txtType02.setBackgroundColor(Color.parseColor("#6c296a"))
                        }
                        "normal" -> {
                            txtType02.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_normal,
                                0,
                                0,
                                0
                            )
                            txtType02.setBackgroundColor(Color.parseColor("#818054"))
                        }

                        "dark" -> {
                            txtType02.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_dark,
                                0,
                                0,
                                0
                            )
                            txtType02.setBackgroundColor(Color.parseColor("#413229"))
                        }
                        "dragon" -> {
                            txtType02.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_dragon,
                                0,
                                0,
                                0
                            )
                            txtType02.setBackgroundColor(Color.parseColor("#4403e1"))
                        }
                        "electric" -> {
                            txtType02.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_electric,
                                0,
                                0,
                                0
                            )
                            txtType02.setBackgroundColor(Color.parseColor("#f76b2c"))
                        }
                        "fairy" -> {
                            txtType02.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_fairy,
                                0,
                                0,
                                0
                            )
                            txtType02.setBackgroundColor(Color.parseColor("#c34c87"))
                        }
                        "fighting" -> {
                            txtType02.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_fighting,
                                0,
                                0,
                                0
                            )
                            txtType02.setBackgroundColor(Color.parseColor("#831f1b"))
                        }
                        "fire" -> {
                            txtType02.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_fire,
                                0,
                                0,
                                0
                            )
                            txtType02.setBackgroundColor(Color.parseColor("#ee303e"))
                        }
                        "ghost" -> {
                            txtType02.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_ghost,
                                0,
                                0,
                                0
                            )
                            txtType02.setBackgroundColor(Color.parseColor("#4e3b66"))
                        }
                        "ground" -> {
                            txtType02.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_ground,
                                0,
                                0,
                                0
                            )
                            txtType02.setBackgroundColor(Color.parseColor("#d3a328"))
                        }
                        "ice" -> {
                            txtType02.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_ice,
                                0,
                                0,
                                0
                            )
                            txtType02.setBackgroundColor(Color.parseColor("#5ec5c0"))
                        }
                        "psychic" -> {
                            txtType02.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_psychic,
                                0,
                                0,
                                0
                            )
                            txtType02.setBackgroundColor(Color.parseColor("#f60b53"))
                        }
                        "rock" -> {
                            txtType02.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_rock,
                                0,
                                0,
                                0
                            )
                            txtType02.setBackgroundColor(Color.parseColor("#7b6d24"))
                        }
                        "steel" -> {
                            txtType02.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_steel,
                                0,
                                0,
                                0
                            )
                            txtType02.setBackgroundColor(Color.parseColor("#8989af"))
                        }
                        "water" -> {
                            txtType02.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.ic_water,
                                0,
                                0,
                                0
                            )
                            txtType02.setBackgroundColor(Color.parseColor("#1d5ee9"))
                        }

                    }
                } else {
                    txtType02.visibility = View.GONE
                }
            }
        }

    }
}
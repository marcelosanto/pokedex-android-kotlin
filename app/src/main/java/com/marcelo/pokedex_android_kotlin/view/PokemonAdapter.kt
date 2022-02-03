package com.marcelo.pokedex_android_kotlin.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcelo.pokedex_android_kotlin.R
import com.marcelo.pokedex_android_kotlin.domain.Pokemon

class PokemonAdapter(
    private val items: List<Pokemon?>
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>(), Filterable {

    private lateinit var mListener: onItemClickListener
    var list: List<Pokemon?> = mutableListOf<Pokemon>()

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    init {
        list = items
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)

        return ViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.bindView(item)
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }


        @SuppressLint("SetTextI18n")
        fun bindView(item: Pokemon?) = with(itemView) {
            val imgPokemon = findViewById<ImageView>(R.id.imgPokemon)
            val txtId = findViewById<TextView>(R.id.txt_idNumber)
            val txtName = findViewById<TextView>(R.id.txt_pokeName)
            val txtType01 = findViewById<TextView>(R.id.txt_type01)
            val txtType02 = findViewById<TextView>(R.id.txt_type02)
            val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout)



            item?.let {
                Glide.with(itemView.context).load(it.imageUrl).into(imgPokemon)

                txtId.text = "#${item.formattedNumber}"
                txtName.text = captalizerText(item.name)
                txtType01.text = captalizerText(item.types[0].name)

                changeColorForBackandLabel(item.types[0].name, txtType01, constraintLayout)

                if (item.types.size > 1) {
                    txtType02.visibility = View.VISIBLE
                    txtType02.text = captalizerText(item.types[1].name)

                    changeColorForBackandLabel(item.types[1].name, txtType02)
                } else {
                    txtType02.visibility = View.GONE
                }
            }
        }


        private fun changeColorForBackandLabel(
            type: String,
            txt: TextView,
            layout: ConstraintLayout? = null
        ) {
            when (type) {
                "flying" -> {
                    txt.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_flying,
                        0,
                        0,
                        0
                    )
                    txt.setBackgroundColor(Color.parseColor("#a385e0"))
                    layout?.setBackgroundColor(Color.parseColor("#614f86"))

                }
                "grass" -> {
                    txt.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_grass,
                        0,
                        0,
                        0
                    )
                    txt.setBackgroundColor(Color.parseColor("#56972f"))
                    layout?.setBackgroundColor(Color.parseColor("#7AC74C"))
                }
                "bug" -> {
                    txt.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_bug,
                        0,
                        0,
                        0
                    )
                    txt.setBackgroundColor(Color.parseColor("#6a7611"))
                    layout?.setBackgroundColor(Color.parseColor("#A6B91A"))
                }
                "poison" -> {
                    txt.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_poison,
                        0,
                        0,
                        0
                    )
                    txt.setBackgroundColor(Color.parseColor("#6c296a"))
                    layout?.setBackgroundColor(Color.parseColor("#A33EA1"))
                }
                "normal" -> {
                    txt.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_normal,
                        0,
                        0,
                        0
                    )
                    txt.setBackgroundColor(Color.parseColor("#818054"))
                    layout?.setBackgroundColor(Color.parseColor("#A8A77A"))
                }

                "dark" -> {
                    txt.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_dark,
                        0,
                        0,
                        0
                    )
                    txt.setBackgroundColor(Color.parseColor("#413229"))
                    layout?.setBackgroundColor(Color.parseColor("#705746"))
                }
                "dragon" -> {
                    txt.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_dragon,
                        0,
                        0,
                        0
                    )
                    txt.setBackgroundColor(Color.parseColor("#4403e1"))
                    layout?.setBackgroundColor(Color.parseColor("#6F35FC"))
                }
                "electric" -> {
                    txt.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_electric,
                        0,
                        0,
                        0
                    )
                    txt.setBackgroundColor(Color.parseColor("#f76b2c"))
                    layout?.setBackgroundColor(Color.parseColor("#F7D02C"))
                }
                "fairy" -> {
                    txt.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_fairy,
                        0,
                        0,
                        0
                    )
                    txt.setBackgroundColor(Color.parseColor("#c34c87"))
                    layout?.setBackgroundColor(Color.parseColor("#D685AD"))
                }
                "fighting" -> {
                    txt.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_fighting,
                        0,
                        0,
                        0
                    )
                    txt.setBackgroundColor(Color.parseColor("#831f1b"))
                    layout?.setBackgroundColor(Color.parseColor("#C22E28"))
                }
                "fire" -> {
                    txt.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_fire,
                        0,
                        0,
                        0
                    )
                    txt.setBackgroundColor(Color.parseColor("#c25c10"))
                    layout?.setBackgroundColor(Color.parseColor("#EE8130"))
                }
                "ghost" -> {
                    txt.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_ghost,
                        0,
                        0,
                        0
                    )
                    txt.setBackgroundColor(Color.parseColor("#4e3b66"))
                    layout?.setBackgroundColor(Color.parseColor("#735797"))
                }
                "ground" -> {
                    txt.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_ground,
                        0,
                        0,
                        0
                    )
                    txt.setBackgroundColor(Color.parseColor("#d3a328"))
                    layout?.setBackgroundColor(Color.parseColor("#E2BF65"))
                }
                "ice" -> {
                    txt.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_ice,
                        0,
                        0,
                        0
                    )
                    txt.setBackgroundColor(Color.parseColor("#5ec5c0"))
                    layout?.setBackgroundColor(Color.parseColor("#96D9D6"))
                }
                "psychic" -> {
                    txt.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_psychic,
                        0,
                        0,
                        0
                    )
                    txt.setBackgroundColor(Color.parseColor("#f60b53"))
                    layout?.setBackgroundColor(Color.parseColor("#F95587"))
                }
                "rock" -> {
                    txt.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_rock,
                        0,
                        0,
                        0
                    )
                    txt.setBackgroundColor(Color.parseColor("#7b6d24"))
                    layout?.setBackgroundColor(Color.parseColor("#B6A136"))
                }
                "steel" -> {
                    txt.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_steel,
                        0,
                        0,
                        0
                    )
                    txt.setBackgroundColor(Color.parseColor("#8989af"))
                    layout?.setBackgroundColor(Color.parseColor("#B7B7CE"))
                }
                "water" -> {
                    txt.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_water,
                        0,
                        0,
                        0
                    )
                    txt.setBackgroundColor(Color.parseColor("#1d5ee9"))
                    layout?.setBackgroundColor(Color.parseColor("#6390F0"))
                }

            }
        }

    }

    override fun getFilter(): Filter {
        Log.d("TAG", "getFilter: oiee")
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    list = items as ArrayList<Pokemon>
                } else {
                    val resultList = ArrayList<Pokemon>()
                    for (row in list) {
                        if (row?.name?.contains(constraint.toString()) == true) {
                            resultList.add(row)
                        }
                    }
                    list = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = list
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                list = results?.values as ArrayList<Pokemon>
                notifyDataSetChanged()
            }
        }
    }
}







package com.marcelo.pokedex_android_kotlin.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcelo.pokedex_android_kotlin.R
import com.marcelo.pokedex_android_kotlin.domain.Pokemon

class PokemonAdapter(
    private val items: List<Pokemon?>
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

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
            val imgType01 = findViewById<ImageView>(R.id.img_type01)
            val type01Layout = findViewById<CardView>(R.id.type01_layout)
            val txtType02 = findViewById<TextView>(R.id.txt_type02)
            val imgType02 = findViewById<ImageView>(R.id.img_type02)
            val type02Layout = findViewById<CardView>(R.id.type02_layout)
            val cardView = findViewById<CardView>(R.id.cardview_pokemon_item)



            item?.let {
                Glide.with(itemView.context).load(it.imageUrl).into(imgPokemon)

                txtId.text = "#${item.formattedNumber}"
                txtName.text = captalizerText(item.name)
                txtType01.text = captalizerText(item.types[0].name)

                changeColorForBackandLabel(item.types[0].name, imgType01, cardView, type01Layout)

                if (item.types.size > 1) {
                    type02Layout.visibility = View.VISIBLE
                    txtType02.text = captalizerText(item.types[1].name)
                    changeColorForBackandLabel(item.types[1].name, imgType02, null, type02Layout)
                } else {
                    type02Layout.visibility = View.INVISIBLE
                }
            }
        }


        private fun changeColorForBackandLabel(
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

    }

}







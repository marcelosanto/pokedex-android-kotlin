package com.marcelo.pokedex_android_kotlin.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcelo.pokedex_android_kotlin.R
import com.marcelo.pokedex_android_kotlin.domain.Pokemon
import com.marcelo.pokedex_android_kotlin.utils.Const.changeColorForBackandLabel

class PokemonAdapterDiff(
    private val onItemClickListener: (Int) -> Unit
) : ListAdapter<Pokemon, PokemonAdapterDiff.ViewHolder>(PokemonDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bindView(pokemon)
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindView(item: Pokemon) = with(itemView) {
            val imgPokemon = findViewById<ImageView>(R.id.item_pokemon_img)
            val txtId = findViewById<TextView>(R.id.item_pokemon_id)
            val txtName = findViewById<TextView>(R.id.item_pokemon_name)
            val txtType01 = findViewById<TextView>(R.id.txt_type01)
            val imgType01 = findViewById<ImageView>(R.id.img_type01)
            val type01Layout = findViewById<CardView>(R.id.type01_layout)
            val txtType02 = findViewById<TextView>(R.id.txt_type02)
            val imgType02 = findViewById<ImageView>(R.id.img_type02)
            val type02Layout = findViewById<CardView>(R.id.type02_layout)
            val cardView = findViewById<CardView>(R.id.cardview_pokemon_item)

            cardView.setOnClickListener {
                onItemClickListener.invoke(item.id.toInt() - 1)
            }


            item.let {
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


    }

}

class PokemonDiffCallback : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }

}






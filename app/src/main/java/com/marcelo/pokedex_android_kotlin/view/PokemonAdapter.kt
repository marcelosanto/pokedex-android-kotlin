package com.marcelo.pokedex_android_kotlin.view

import android.annotation.SuppressLint
import android.util.Log
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
import com.marcelo.pokedex_android_kotlin.utils.Const.changeColorForBackandLabel

class PokemonAdapter(
    private val pokemons: List<Pokemon>,
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = pokemons[position]
        holder.bindView(item)
    }

    override fun getItemCount(): Int {
        Log.i("TAG", "onCreate: ${pokemons.size}")
        return pokemons.size
    }


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindView(item: Pokemon) = with(itemView) {
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







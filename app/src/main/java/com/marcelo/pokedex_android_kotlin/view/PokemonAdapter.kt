package com.marcelo.pokedex_android_kotlin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.pokedex_android_kotlin.R
import com.marcelo.pokedex_android_kotlin.domain.Pokemon

class PokemonAdapter(
    private val items: List<Pokemon>
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
        fun bindView(item: Pokemon) = with(itemView) {
            val imgPokemon = findViewById<ImageView>(R.id.imgPokemon)
            val txtId = findViewById<TextView>(R.id.txt_idNumber)
            val txtName = findViewById<TextView>(R.id.txt_pokeName)
            val txtType01 = findViewById<TextView>(R.id.txt_type01)
            val txtType02 = findViewById<TextView>(R.id.txt_type02)

            // TODO: Load image with Glide

            txtId.text = "#${item.number}"
            txtName.text = item.name
            txtType01.text = item.types[0].name

            if (item.types.size > 1) {
                txtType02.visibility = View.VISIBLE
                txtType02.text = item.types[1].name
            } else {
                txtType02.visibility = View.GONE
            }
        }

    }
}
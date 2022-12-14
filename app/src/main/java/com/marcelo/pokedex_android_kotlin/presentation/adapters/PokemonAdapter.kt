package com.marcelo.pokedex_android_kotlin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marcelo.pokedex_android_kotlin.databinding.PokemonCardBinding
import com.marcelo.pokedex_android_kotlin.domain.Pokemon
import com.marcelo.pokedex_android_kotlin.presentation.fragments.captalizerText
import com.marcelo.pokedex_android_kotlin.utils.Const.changeColorForBackandLabel

class PokemonAdapter() : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PokemonCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = differ.currentList[position]
        holder.bindView(pokemon)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: PokemonCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(pokemon: Pokemon) = with(itemView) {

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(pokemon)
                }
            }

            binding.apply {
                with(this) {
                    Glide.with(itemView.context).load(pokemon.imageUrl).into(itemPokemonImg)
                    itemPokemonId.text = "#${pokemon.formattedNumber}"
                    itemPokemonName.text = captalizerText(pokemon.name)
                    txtType01.text = captalizerText(pokemon.types[0].name)
                    changeColorForBackandLabel(
                        pokemon.types[0].name,
                        imgType01,
                        cardviewPokemonItem,
                        type01Layout
                    )

                    if (pokemon.types.size > 1) {
                        type02Layout.visibility = View.VISIBLE
                        txtType02.text = captalizerText(pokemon.types[1].name)
                        changeColorForBackandLabel(
                            pokemon.types[1].name,
                            imgType02,
                            null,
                            type02Layout
                        )
                    } else {
                        type02Layout.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((Pokemon) -> Unit)? = null

    fun setOnItemClickListener(listener: (Pokemon) -> Unit) {
        onItemClickListener = listener
    }
}







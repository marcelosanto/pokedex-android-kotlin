package com.marcelo.pokedex_android_kotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.card.MaterialCardView
import com.marcelo.pokedex_android_kotlin.api.model.PokemonModel
import com.marcelo.pokedex_android_kotlin.databinding.ActivityMainBinding
import com.marcelo.pokedex_android_kotlin.domain.Pokemon
import com.marcelo.pokedex_android_kotlin.pokedex.presentation.viewmodel.PokemonViewModel
import com.marcelo.pokedex_android_kotlin.utils.Const.colorType
import com.marcelo.pokedex_android_kotlin.view.PokemonActivity
import com.marcelo.pokedex_android_kotlin.view.PokemonAdapterDiff

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: PokemonViewModel
    private lateinit var adapter: PokemonAdapterDiff

    private var filterArrayList = mutableListOf<Pokemon>()
    private var pokemonsArraysList = mutableListOf<Pokemon>()

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)


        adapter = PokemonAdapterDiff { position ->
            val intent = Intent(this@MainActivity, PokemonActivity::class.java)
            val poke = PokemonModel(
                pokemonsArraysList[position].id,
                pokemonsArraysList[position].name,
                pokemonsArraysList[position].imageUrl,
                pokemonsArraysList[position].types,
                pokemonsArraysList[position].weight,
                pokemonsArraysList[position].height,
                pokemonsArraysList[position].base_experience,
                pokemonsArraysList[position].abilities,
                pokemonsArraysList[position].species,
                pokemonsArraysList[position].biography,
                pokemonsArraysList[position].base_happiness,
                pokemonsArraysList[position].capture_rate,
                pokemonsArraysList[position].growth_rate,
                pokemonsArraysList[position].evolutions,
                pokemonsArraysList[position].stats

            )
            intent.putExtra("pokemon", poke)
            startActivity(intent)
        }

        binding.rvPokemons.let { rv ->
            rv.layoutManager = LinearLayoutManager(this)
            rv.adapter = adapter
        }

        viewModel.pokemons.observe(this, Observer {
            pokemonsArraysList.addAll(it.requireNoNulls())
            adapter.submitList(pokemonsArraysList)
        })

        binding.btnSort.setOnClickListener { showSortFilter() }

        // btnFilters.setOnClickListener { showFiltersAdvanced() }
        //btnGenerations.setOnClickListener { showGenerationsFilters() }


        binding.inputSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.search(newText)
                return true
            }

        })
    }

    private fun buttonAction(
        type: String,
        cardView: MaterialCardView,
        isBoolType: Boolean,
        imgCardView: ImageView,
        array: ArrayList<String>
    ) {
        var isTrueType = isBoolType

        cardView.setOnClickListener {
            isTrueType = if (isTrueType) {
                cardView.setCardBackgroundColor(Color.parseColor(colorType(type)))
                imgCardView.setColorFilter(Color.WHITE)
                array.add(type)
                !isTrueType
            } else {
                cardView.setCardBackgroundColor(Color.WHITE)
                imgCardView.setColorFilter(Color.parseColor(colorType(type)))
                array.remove(type)
                !isTrueType
            }

        }
    }

    private fun activeFilterInTypesOrWeaknesses(
        type: String,
        cardView: MaterialCardView,
        imgCardView: ImageView
    ) {
        cardView.setCardBackgroundColor(Color.parseColor(colorType(type)))
        imgCardView.setColorFilter(Color.WHITE)
    }


    @SuppressLint("InflateParams")
    private fun showSortFilter() {

        val dialog = BottomSheetDialog(this, R.style.MyTransparentBottomSheetDialogTheme)
        dialog.setContentView(R.layout.bottom_sheet_item_sort)

        val btnSmall = dialog.findViewById<Button>(R.id.btn_small)
        val btnHigh = dialog.findViewById<Button>(R.id.btn_high)
        val btnAZ = dialog.findViewById<Button>(R.id.btn_a_z)
        val btnZA = dialog.findViewById<Button>(R.id.btn_z_a)


        btnSmall?.setOnClickListener {
            smallNumberPokemonFirst()
            dialog.dismiss()
        }
        btnHigh?.setOnClickListener {
            dialog.dismiss()
            highNumberPokemonFirst()
        }
        btnAZ?.setOnClickListener {
            dialog.dismiss()
            orderByNameAandZ()
        }
        btnZA?.setOnClickListener {
            dialog.dismiss()
            orderByNameZandA()
        }

        dialog.show()
    }


    private fun orderByNameZandA() {
        adapter.submitList(pokemonsArraysList.sortedByDescending { it.name })
    }

    private fun orderByNameAandZ() {
        adapter.submitList(pokemonsArraysList.sortedBy { it.name })
    }

    private fun highNumberPokemonFirst() {
        adapter.submitList(pokemonsArraysList.sortedByDescending { it.id.toInt() })
    }

    private fun smallNumberPokemonFirst() {
        adapter.submitList(pokemonsArraysList.sortedBy { it.id.toInt() })
    }
}




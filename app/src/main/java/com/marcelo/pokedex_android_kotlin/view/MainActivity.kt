package com.marcelo.pokedex_android_kotlin.view

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
import com.marcelo.pokedex_android_kotlin.R
import com.marcelo.pokedex_android_kotlin.api.model.PokemonModel
import com.marcelo.pokedex_android_kotlin.domain.Pokemon
import com.marcelo.pokedex_android_kotlin.utils.Const.colorType
import com.marcelo.pokedex_android_kotlin.viewmodel.PokemonViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PokemonViewModel
    private lateinit var adapter: PokemonAdapterDiff

    private var filterArrayList = mutableListOf<Pokemon>()
    private var pokemonsArraysList = mutableListOf<Pokemon>()

    //val recyclerView by lazy { findViewById<RecyclerView>(R.id.rvPokemons) }

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)
        
        recyclerView = findViewById<RecyclerView>(R.id.rvPokemons)
        recyclerView.layoutManager = LinearLayoutManager(this)
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
        recyclerView.adapter = adapter

        viewModel.pokemons.observe(this, Observer {
            pokemonsArraysList.addAll(it.requireNoNulls())
            adapter.submitList(pokemonsArraysList)
        })


        val btnSort: ImageButton = findViewById(R.id.Btn_sort)

        btnSort.setOnClickListener { showSortFilter() }

        val btnFilters: ImageButton = findViewById(R.id.btn_filters)

        // btnFilters.setOnClickListener { showFiltersAdvanced() }

        val btnGenerations: ImageButton = findViewById(R.id.btn_generations)

        //btnGenerations.setOnClickListener { showGenerationsFilters() }

        val inputSearch: SearchView = findViewById(R.id.inputSearch)

//        inputSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                adapter.search(newText)
//
//                return true
//            }
//
//        })
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
        pokemonsArraysList.sortedByDescending { it.name }
        adapter.notifyDataSetChanged()
    }

    private fun orderByNameAandZ() {
        pokemonsArraysList.sortedBy { it.name }
        adapter.notifyDataSetChanged()
    }

    private fun highNumberPokemonFirst() {
        pokemonsArraysList.sortedByDescending { it.id.toInt() }
        adapter.notifyDataSetChanged()
    }

    private fun smallNumberPokemonFirst() {
        pokemonsArraysList.sortedBy { it.id.toInt() }
        adapter.notifyDataSetChanged()
    }
}




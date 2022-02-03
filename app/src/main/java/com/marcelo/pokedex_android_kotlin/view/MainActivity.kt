package com.marcelo.pokedex_android_kotlin.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputEditText
import com.marcelo.pokedex_android_kotlin.R
import com.marcelo.pokedex_android_kotlin.api.model.PokemonModel
import com.marcelo.pokedex_android_kotlin.domain.Pokemon
import com.marcelo.pokedex_android_kotlin.viewmodel.PokemonViewModel
import com.marcelo.pokedex_android_kotlin.viewmodel.PokemonViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var tempArrayList: List<Pokemon>

    private var filterArrayList: ArrayList<Pokemon> = ArrayList()


    private val recyclerView by lazy {
        findViewById<RecyclerView>(R.id.rvPokemons)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, PokemonViewModelFactory())
            .get(PokemonViewModel::class.java)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.pokemons.observe(this, Observer {
            loadRecyclerView(it)
        })


        val btnSort: ImageButton = findViewById(R.id.Btn_sort)

        btnSort.setOnClickListener { showSortFilter() }

        val btnFilters: ImageButton = findViewById(R.id.btn_filters)

        btnFilters.setOnClickListener { showFiltersAdvanced() }

        val inputSearch: TextInputEditText = findViewById(R.id.inputSearch)


        inputSearch.doAfterTextChanged { text ->

            if (text!!.isNotEmpty()) {
                for (pokemon in tempArrayList) {
                    if (pokemon.name.contains(text.toString()) || pokemon.id.contains(text.toString())) {
                        Log.i("FILTRO", "pokemon: ${pokemon.name} ")
                        if (!filterArrayList.contains(pokemon)) {
                            filterArrayList.add(pokemon)
                        }
                    } else {
                        filterArrayList.remove(pokemon)
                    }
                    adapterInRecyclerView(filterArrayList)
                }
                Log.i("FILTRO", "filterArray: ${filterArrayList.size} ")
                // adapterInRecyclerView(filterArrayList)

            } else {
                filterArrayList.clear()
                Log.i("FILTRO", "tempArray: ${tempArrayList.size} ")
                adapterInRecyclerView(tempArrayList)
            }

        }
    }

    @SuppressLint("InflateParams")
    private fun showFiltersAdvanced() {
        val view: View = layoutInflater.inflate(R.layout.item_filters, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()

        val cardBug = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_bug)

        val imagemBug = dialog.findViewById<ImageView>(R.id.type_bug_img)

        val cardTypeNormal = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_normal)
        val imagemTypeNormal = dialog.findViewById<ImageView>(R.id.type_normal_img)

        var isTrueTypeBug: Boolean = true

        cardBug?.setOnClickListener {
            isTrueTypeBug = if (isTrueTypeBug) {
                cardBug.setCardBackgroundColor(Color.parseColor("#6a7611"))
                imagemBug?.setColorFilter(Color.WHITE)
                !isTrueTypeBug
            } else {
                cardBug.setCardBackgroundColor(Color.WHITE)
                imagemBug?.setColorFilter(Color.parseColor("#6a7611"))
                !isTrueTypeBug
            }

        }

        var isTrueTypeNormal: Boolean = true

        cardTypeNormal?.setOnClickListener {
            isTrueTypeNormal = if (isTrueTypeNormal) {
                cardTypeNormal.setCardBackgroundColor(Color.parseColor("#818054"))
                imagemTypeNormal?.setColorFilter(Color.WHITE)
                !isTrueTypeNormal
            } else {
                cardTypeNormal.setCardBackgroundColor(Color.WHITE)
                imagemTypeNormal?.setColorFilter(Color.parseColor("#818054"))
                !isTrueTypeNormal
            }

        }

    }

    @SuppressLint("InflateParams")
    private fun showSortFilter() {
        val view: View = layoutInflater.inflate(R.layout.item_sort, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.show()


        val btnSmall = dialog.findViewById<Button>(R.id.btn_small)
        val btnHigh = dialog.findViewById<Button>(R.id.btn_high)
        val btnAZ = dialog.findViewById<Button>(R.id.btn_a_z)
        val btnZA = dialog.findViewById<Button>(R.id.btn_z_a)


        btnSmall?.setOnClickListener {
            smallNumberPokemonFirst()
            dialog.dismiss()
            Toast.makeText(this, "Small Button", Toast.LENGTH_LONG).show()
        }
        btnHigh?.setOnClickListener {
            dialog.dismiss()
            highNumberPokemonFirst()
            Toast.makeText(this, "High Button", Toast.LENGTH_LONG).show()
        }
        btnAZ?.setOnClickListener {
            dialog.dismiss()
            orderByNameAandZ()
            Toast.makeText(this, "A-Z Button", Toast.LENGTH_LONG).show()
        }
        btnZA?.setOnClickListener {
            dialog.dismiss()
            orderByNameZandA()
            Toast.makeText(this, "Z-A Button", Toast.LENGTH_LONG).show()

        }
    }

    private fun orderByNameZandA() {
        loadRecyclerView(tempArrayList.sortedByDescending { it.name })
    }

    private fun orderByNameAandZ() {
        loadRecyclerView(tempArrayList.sortedBy { it.name })
    }

    private fun highNumberPokemonFirst() {
        loadRecyclerView(tempArrayList.sortedByDescending { it.id.toInt() })

    }

    private fun smallNumberPokemonFirst() {
        loadRecyclerView(tempArrayList.sortedBy { it.id.toInt() })
    }

    private fun loadRecyclerView(pokemons: List<Pokemon?>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        tempArrayList = pokemons.map { it!!.copy() }
        adapterInRecyclerView(tempArrayList)
    }


    private fun adapterInRecyclerView(pokemons: List<Pokemon?>) {

        val adapter = PokemonAdapter(pokemons)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : PokemonAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, PokemonActivity::class.java)

                val poke = PokemonModel(
                    pokemons[position]!!.id,
                    pokemons[position]!!.name,
                    pokemons[position]!!.imageUrl,
                    pokemons[position]!!.types,
                    pokemons[position]!!.weight,
                    pokemons[position]!!.height,
                    pokemons[position]!!.base_experience,
                    pokemons[position]!!.abilities,
                    pokemons[position]!!.species,
                    pokemons[position]!!.biography,
                    pokemons[position]!!.base_happiness,
                    pokemons[position]!!.capture_rate,
                    pokemons[position]!!.growth_rate,
                    pokemons[position]!!.evolutions,
                    pokemons[position]!!.stats

                )
                intent.putExtra("pokemon", poke)

                startActivity(intent)
            }
        })
    }


}




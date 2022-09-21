package com.marcelo.pokedex_android_kotlin.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SearchView
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

    private var filterArrayList: ArrayList<Pokemon> = ArrayList()

    val recyclerView by lazy { findViewById<RecyclerView>(R.id.rvPokemons) }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var PokemonsArraysList: List<Pokemon> = emptyList()

        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        val adapter = PokemonAdapter(PokemonsArraysList) { position ->
            val intent = Intent(this@MainActivity, PokemonActivity::class.java)
            val poke = PokemonModel(
                PokemonsArraysList[position].id,
                PokemonsArraysList[position].name,
                PokemonsArraysList[position].imageUrl,
                PokemonsArraysList[position].types,
                PokemonsArraysList[position].weight,
                PokemonsArraysList[position].height,
                PokemonsArraysList[position].base_experience,
                PokemonsArraysList[position].abilities,
                PokemonsArraysList[position].species,
                PokemonsArraysList[position].biography,
                PokemonsArraysList[position].base_happiness,
                PokemonsArraysList[position].capture_rate,
                PokemonsArraysList[position].growth_rate,
                PokemonsArraysList[position].evolutions,
                PokemonsArraysList[position].stats

            )
            intent.putExtra("pokemon", poke)
            startActivity(intent)
        }

        viewModel.pokemons.observe(this, Observer {
            PokemonsArraysList = it.requireNoNulls()
            Log.i("TAG", "onCreate: ${PokemonsArraysList.size}")
            adapter.notifyDataSetChanged()
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val btnSort: ImageButton = findViewById(R.id.Btn_sort)

        btnSort.setOnClickListener { showSortFilter() }

        val btnFilters: ImageButton = findViewById(R.id.btn_filters)

        // btnFilters.setOnClickListener { showFiltersAdvanced() }

        val btnGenerations: ImageButton = findViewById(R.id.btn_generations)

        //btnGenerations.setOnClickListener { showGenerationsFilters() }

        val inputSearch: SearchView = findViewById(R.id.inputSearch)

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

        //val view: View = layoutInflater.inflate(R.layout.bottom_shee_item_sort, null)
        val dialog = BottomSheetDialog(this, R.style.MyTransparentBottomSheetDialogTheme)
        dialog.setContentView(R.layout.bottom_sheet_item_sort)

        val btnSmall = dialog.findViewById<Button>(R.id.btn_small)
        val btnHigh = dialog.findViewById<Button>(R.id.btn_high)
        val btnAZ = dialog.findViewById<Button>(R.id.btn_a_z)
        val btnZA = dialog.findViewById<Button>(R.id.btn_z_a)

        //filterArrayList.addAll(PokemonsArraysList)

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
        //adapterInRecyclerView(filterArrayList.sortedByDescending { it.name })

    }

    private fun orderByNameAandZ() {
        //adapterInRecyclerView(filterArrayList.sortedBy { it.name })
    }

    private fun highNumberPokemonFirst() {
        //adapterInRecyclerView(filterArrayList.sortedByDescending { it.id.toInt() })
    }

    private fun smallNumberPokemonFirst() {
        // adapterInRecyclerView(filterArrayList.sortedBy { it.id.toInt() })
    }

    private fun loadRecyclerView(pokemons: List<Pokemon?>) {


        // adapterInRecyclerView(PokemonsArraysList)
    }


}




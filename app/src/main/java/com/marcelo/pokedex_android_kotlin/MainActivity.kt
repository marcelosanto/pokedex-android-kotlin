package com.marcelo.pokedex_android_kotlin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.marcelo.pokedex_android_kotlin.data.model.ModelPokemon
import com.marcelo.pokedex_android_kotlin.databinding.ActivityMainBinding
import com.marcelo.pokedex_android_kotlin.presentation.components.BottomFilterDialog
import com.marcelo.pokedex_android_kotlin.presentation.viewmodel.PokemonViewModel
import com.marcelo.pokedex_android_kotlin.presentation.viewmodel.PokemonViewModelFactory
import com.marcelo.pokedex_android_kotlin.utils.Const.colorType
import com.marcelo.pokedex_android_kotlin.utils.Const.highNumberPokemonFirst
import com.marcelo.pokedex_android_kotlin.utils.Const.orderByNameAandZ
import com.marcelo.pokedex_android_kotlin.utils.Const.orderByNameZandA
import com.marcelo.pokedex_android_kotlin.utils.Const.smallNumberPokemonFirst
import com.marcelo.pokedex_android_kotlin.view.PokemonActivity
import com.marcelo.pokedex_android_kotlin.view.PokemonAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var factory: PokemonViewModelFactory
    lateinit var viewModel: PokemonViewModel

    private lateinit var adapter: PokemonAdapter

    private var pokemonsArraysList = mutableListOf<ModelPokemon>()

    private lateinit var recyclerView: RecyclerView

    val offset = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, factory)[PokemonViewModel::class.java]

        adapter = PokemonAdapter()

        adapter.setOnItemClickListener { pokemon ->
            val intent = Intent(this@MainActivity, PokemonActivity::class.java)
            intent.putExtra("POKEMON", pokemon)
            startActivity(intent)
        }

        initRecyclerView()
        getAllPokemons()

        binding.btnSort.setOnClickListener { showSortFilter() }

        // btnFilters.setOnClickListener { showFiltersAdvanced() }
        //btnGenerations.setOnClickListener { showGenerationsFilters() }


        binding.inputSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                updateList(viewModel.filterList(newText, pokemonsArraysList))
                return true
            }

        })
    }

    fun updateList(filteredList: MutableList<ModelPokemon>) {
        adapter.differ.submitList(filteredList)
    }

    private fun getAllPokemons() {
        viewModel.getAllPokemons(offset)

        viewModel.pokemons.observe(this, Observer {
            pokemonsArraysList = it as MutableList<ModelPokemon>
            adapter.differ.submitList(it)
        })
    }

    private fun initRecyclerView() {
        binding.rvPokemons.let { rv ->
            rv.layoutManager = LinearLayoutManager(this)
            rv.adapter = adapter
        }
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

    private fun showSortFilter() {

        val bottomSheetDialog = BottomFilterDialog()
        bottomSheetDialog.show(supportFragmentManager, "myBottomSheetDialog")
        bottomSheetDialog.setOnItemClickListener {
            when (it) {
                "small" -> {
                    updateList(smallNumberPokemonFirst(pokemonsArraysList))
                    bottomSheetDialog.dismiss()
                }
                "high" -> {
                    updateList(highNumberPokemonFirst(pokemonsArraysList))
                    bottomSheetDialog.dismiss()
                }
                "a-z" -> {
                    updateList(orderByNameAandZ(pokemonsArraysList))
                    bottomSheetDialog.dismiss()
                }
                "z-a" -> {
                    updateList(orderByNameZandA(pokemonsArraysList))
                    bottomSheetDialog.dismiss()
                }
            }
        }

    }
}




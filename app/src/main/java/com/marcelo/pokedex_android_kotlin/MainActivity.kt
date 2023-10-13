package com.marcelo.pokedex_android_kotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marcelo.pokedex_android_kotlin.data.model.ModelPokemon
import com.marcelo.pokedex_android_kotlin.databinding.ActivityMainBinding
import com.marcelo.pokedex_android_kotlin.presentation.components.BottomFilterDialog
import com.marcelo.pokedex_android_kotlin.presentation.viewmodel.PokemonViewModel
import com.marcelo.pokedex_android_kotlin.presentation.viewmodel.PokemonViewModelFactory
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

   // val offset = 0

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
        getAllPokemons(0)

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

    private fun getAllPokemons(offset: Int) {
        viewModel.getAllPokemons(offset)

        viewModel.pokemons.observe(this, Observer {
            pokemonsArraysList = it as MutableList<ModelPokemon>
            adapter.differ.submitList(it)
        })
    }

    private fun initRecyclerView() {
        val rv = binding.rvPokemons
            rv.layoutManager = LinearLayoutManager(this)
            rv.adapter = adapter

            rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                    val totalItemCount = adapter.itemCount

                    if (lastVisibleItemPosition == totalItemCount - 1) {
                        getAllPokemons(20)
                        Log.d("TAG", "onScrolled: CHEGUEI AO FIM")
                    }
                }
            })



    }

    fun updateList(filteredList: MutableList<ModelPokemon>) {
        adapter.differ.submitList(filteredList)
        binding.rvPokemons.smoothScrollToPosition(0)
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




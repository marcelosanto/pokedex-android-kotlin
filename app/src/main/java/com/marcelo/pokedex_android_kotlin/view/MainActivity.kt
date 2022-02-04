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
import java.util.*

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
                    if (pokemon.name.contains(
                            text.toString().lowercase(Locale.getDefault())
                        ) || pokemon.id.contains(text.toString())
                    ) {
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
        val cardTypeNormal = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_normal)
        val cardTypeFire = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_fire)
        val cardTypeWater = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_water)
        val cardTypeEletric = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_eletric)
        val cardTypeGrass = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_grass)
        val cardTypeIce = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_ice)
        val cardTypeFighting = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_fighting)
        val cardTypePoison = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_poison)
        val cardTypeGround = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_ground)
        val cardTypeFlying = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_flying)
        val cardTypePsychic = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_psychic)
        val cardTypeRock = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_rock)
        val cardTypeGhost = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_ghost)
        val cardTypeDragon = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_dragon)
        val cardTypeDark = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_dark)
        val cardTypeSteel = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_steel)
        val cardTypeFairy = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_fairy)

        val imgTypeNormal = dialog.findViewById<ImageView>(R.id.type_normal_img)
        val imgTypeBug = dialog.findViewById<ImageView>(R.id.type_bug_img)
        val imgTypeFire = dialog.findViewById<ImageView>(R.id.type_fire_img)
        val imgTypeWater = dialog.findViewById<ImageView>(R.id.type_water_img)
        val imgTypeEletric = dialog.findViewById<ImageView>(R.id.type_eletric_img)
        val imgTypeGrass = dialog.findViewById<ImageView>(R.id.type_grass_img)
        val imgTypeIce = dialog.findViewById<ImageView>(R.id.type_ice_img)
        val imgTypeFighting = dialog.findViewById<ImageView>(R.id.type_fighting_img)
        val imgTypePoison = dialog.findViewById<ImageView>(R.id.type_poison_img)
        val imgTypeGround = dialog.findViewById<ImageView>(R.id.type_ground_img)
        val imgTypeFlying = dialog.findViewById<ImageView>(R.id.type_flying_img)
        val imgTypePsychic = dialog.findViewById<ImageView>(R.id.type_psychic_img)
        val imgTypeRock = dialog.findViewById<ImageView>(R.id.type_rock_img)
        val imgTypeGhost = dialog.findViewById<ImageView>(R.id.type_ghost_img)
        val imgTypeDragon = dialog.findViewById<ImageView>(R.id.type_dragon_img)
        val imgTypeDark = dialog.findViewById<ImageView>(R.id.type_dark_img)
        val imgTypeSteel = dialog.findViewById<ImageView>(R.id.type_steel_img)
        val imgTypeFairy = dialog.findViewById<ImageView>(R.id.type_fairy_img)

        if (cardBug != null && imgTypeBug != null) {
            buttonAction("bug", cardBug, true, imgTypeBug)
        }

        if (cardTypeNormal != null && imgTypeNormal != null) {
            buttonAction("normal", cardTypeNormal, true, imgTypeNormal)
        }

        if (cardTypeFire != null && imgTypeFire != null) {
            buttonAction("fire", cardTypeFire, true, imgTypeFire)
        }

        if (cardTypeWater != null && imgTypeWater != null) {
            buttonAction("water", cardTypeWater, true, imgTypeWater)
        }

        if (cardTypeEletric != null && imgTypeEletric != null) {
            buttonAction("eletric", cardTypeEletric, true, imgTypeEletric)
        }

        if (cardTypeGrass != null && imgTypeGrass != null) {
            buttonAction("grass", cardTypeGrass, true, imgTypeGrass)
        }

        if (cardTypeIce != null && imgTypeIce != null) {
            buttonAction("ice", cardTypeIce, true, imgTypeIce)
        }

        if (cardTypeFighting != null && imgTypeFighting != null) {
            buttonAction("fighting", cardTypeFighting, true, imgTypeFighting)
        }

        if (cardTypePoison != null && imgTypePoison != null) {
            buttonAction("poison", cardTypePoison, true, imgTypePoison)
        }

        if (cardTypeGround != null && imgTypeGround != null) {
            buttonAction("ground", cardTypeGround, true, imgTypeGround)
        }

        if (cardTypeFlying != null && imgTypeFlying != null) {
            buttonAction("flying", cardTypeFlying, true, imgTypeFlying)
        }

        if (cardTypePsychic != null && imgTypePsychic != null) {
            buttonAction("psychic", cardTypePsychic, true, imgTypePsychic)
        }

        if (cardTypeRock != null && imgTypeRock != null) {
            buttonAction("rock", cardTypeRock, true, imgTypeRock)
        }

        if (cardTypeGhost != null && imgTypeGhost != null) {
            buttonAction("ghost", cardTypeGhost, true, imgTypeGhost)
        }

        if (cardTypeDragon != null && imgTypeDragon != null) {
            buttonAction("dragon", cardTypeDragon, true, imgTypeDragon)
        }

        if (cardTypeDark != null && imgTypeDark != null) {
            buttonAction("dark", cardTypeDark, true, imgTypeDark)
        }

        if (cardTypeSteel != null && imgTypeSteel != null) {
            buttonAction("steel", cardTypeSteel, true, imgTypeSteel)
        }

        if (cardTypeFairy != null && imgTypeFairy != null) {
            buttonAction("fairy", cardTypeFairy, true, imgTypeFairy)
        }


    }

    private fun buttonAction(
        type: String,
        cardView: MaterialCardView,
        isTypeBool: Boolean,
        imgCardView: ImageView
    ) {
        var isTrueType = isTypeBool

        cardView.setOnClickListener {
            isTrueType = if (isTrueType) {
                cardView.setCardBackgroundColor(Color.parseColor(colorType(type)))
                imgCardView.setColorFilter(Color.WHITE)
                !isTrueType
            } else {
                cardView.setCardBackgroundColor(Color.WHITE)
                imgCardView.setColorFilter(Color.parseColor(colorType(type)))
                !isTrueType
            }

        }
    }

    private fun colorType(type: String): String = when (type) {
        "normal" -> "#818054"
        "fire" -> "#c25c10"
        "water" -> "#1d5ee9"
        "eletric" -> "#FFEF00"
        "grass" -> "#56972f"
        "ice" -> "#5ec5c0"
        "fighting" -> "#831f1b"
        "poison" -> "#6c296a"
        "ground" -> "#d3a328"
        "flying" -> "#a385e0"
        "psychic" -> "#f60b53"
        "bug" -> "#6a7611"
        "rock" -> "#7b6d24"
        "ghost" -> "#4e3b66"
        "dragon" -> "#4403e1"
        "dark" -> "#413229"
        "steel" -> "#8989af"
        "fairy" -> "#c34c87"
        else -> "#FFFFFF"
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




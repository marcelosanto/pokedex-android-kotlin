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
import com.marcelo.pokedex_android_kotlin.viewmodel.PokemonViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PokemonViewModel

    private lateinit var tempArrayList: List<Pokemon>
    private var filterArrayList: ArrayList<Pokemon> = ArrayList()
    private var filterCloneTempArrayList: ArrayList<Pokemon> = ArrayList()

    private var filtersTypesList: ArrayList<String> = ArrayList()
    private var filtersWeaknessesList: ArrayList<String> = ArrayList()
    private var filtersHeightsList: ArrayList<String> = ArrayList()
    private var filtersWeightsList: ArrayList<String> = ArrayList()
    private var filtersRangerList: ArrayList<Int> = ArrayList()

    val recyclerView by lazy { findViewById<RecyclerView>(R.id.rvPokemons) }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(PokemonViewModel::class.java)

        viewModel.pokemons.observe(this, Observer {
            loadRecyclerView(it)
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
//                if (query != null) {
//                    searchPokemon(query)
//                }
//                return true
//            }
//
//            override fun onQueryTextChange(newString: String?): Boolean {
//                if (newString != null) {
//                    searchPokemon(newString)
//                }
//                return true
//            }
//
//        })


    }

//    private fun searchPokemon(query: String?) {
//
//        val tempsArray: ArrayList<Pokemon> = ArrayList()
//        if (query != null && filterArrayList.isEmpty()) {
//            for (pokemon in tempArrayList) {
//                if (pokemon.name.contains(query) || pokemon.id.contains(query)) {
//                    Log.i("FILTRO", "pokemon: ${pokemon.name} ")
//                    if (!filterArrayList.contains(pokemon)) {
//                        filterArrayList.add(pokemon)
//                    }
//                } else {
//                    filterArrayList.remove(pokemon)
//                }
//                val unic = filterArrayList.distinct()
//                adapterInRecyclerView(unic)
//            }
//        } else if (query != null && filtersTypesList.isNotEmpty()) {
//
//            for (pokemon in filterArrayList) {
//                if (pokemon.name.contains(query) || pokemon.id.contains(query)
//                ) {
//                    if (!tempsArray.contains(pokemon)) {
//                        tempsArray.add(pokemon)
//                    }
//                } else {
//                    tempsArray.remove(pokemon)
//
//                }
//
//                val unic = tempsArray.distinct()
//                adapterInRecyclerView(unic)
//            }
//        } else {
//            if (filtersTypesList.size > 0) {
//                val unic = filterArrayList.distinct()
//                adapterInRecyclerView(unic)
//            } else {
//                filterArrayList.clear()
//                adapterInRecyclerView(tempArrayList)
//            }
//        }
//    }
//
//    private fun showGenerationsFilters() {
//        val view: View = layoutInflater.inflate(R.layout.item_generation, null)
//        val dialog = BottomSheetDialog(this, R.style.MyTransparentBottomSheetDialogTheme)
//        dialog.setContentView(view)
//        dialog.show()
//    }
//
//    @SuppressLint("InflateParams")
//    private fun showFiltersAdvanced() {
//        val view: View = layoutInflater.inflate(R.layout.item_filters, null)
//        val dialog = BottomSheetDialog(this, R.style.MyTransparentBottomSheetDialogTheme)
//        dialog.setContentView(view)
//        dialog.show()
//
//        Log.i("WEAK", "weak: ${weaknessPokemon("fire")}")
//
//        val cardTypeBug = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_bug)
//        val cardTypeNormal =
//            dialog.findViewById<MaterialCardView>(R.id.card_type_filter_normal)
//        val cardTypeFire = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_fire)
//        val cardTypeWater =
//            dialog.findViewById<MaterialCardView>(R.id.card_type_filter_water)
//        val cardTypeEletric =
//            dialog.findViewById<MaterialCardView>(R.id.card_type_filter_eletric)
//        val cardTypeGrass =
//            dialog.findViewById<MaterialCardView>(R.id.card_type_filter_grass)
//        val cardTypeIce = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_ice)
//        val cardTypeFighting =
//            dialog.findViewById<MaterialCardView>(R.id.card_type_filter_fighting)
//        val cardTypePoison =
//            dialog.findViewById<MaterialCardView>(R.id.card_type_filter_poison)
//        val cardTypeGround =
//            dialog.findViewById<MaterialCardView>(R.id.card_type_filter_ground)
//        val cardTypeFlying =
//            dialog.findViewById<MaterialCardView>(R.id.card_type_filter_flying)
//        val cardTypePsychic =
//            dialog.findViewById<MaterialCardView>(R.id.card_type_filter_psychic)
//        val cardTypeRock = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_rock)
//        val cardTypeGhost =
//            dialog.findViewById<MaterialCardView>(R.id.card_type_filter_ghost)
//        val cardTypeDragon =
//            dialog.findViewById<MaterialCardView>(R.id.card_type_filter_dragon)
//        val cardTypeDark = dialog.findViewById<MaterialCardView>(R.id.card_type_filter_dark)
//        val cardTypeSteel =
//            dialog.findViewById<MaterialCardView>(R.id.card_type_filter_steel)
//        val cardTypeFairy =
//            dialog.findViewById<MaterialCardView>(R.id.card_type_filter_fairy)
//
//        val imgTypeNormal = dialog.findViewById<ImageView>(R.id.type_normal_img)
//        val imgTypeBug = dialog.findViewById<ImageView>(R.id.type_bug_img)
//        val imgTypeFire = dialog.findViewById<ImageView>(R.id.type_fire_img)
//        val imgTypeWater = dialog.findViewById<ImageView>(R.id.type_water_img)
//        val imgTypeEletric = dialog.findViewById<ImageView>(R.id.type_eletric_img)
//        val imgTypeGrass = dialog.findViewById<ImageView>(R.id.type_grass_img)
//        val imgTypeIce = dialog.findViewById<ImageView>(R.id.type_ice_img)
//        val imgTypeFighting = dialog.findViewById<ImageView>(R.id.type_fighting_img)
//        val imgTypePoison = dialog.findViewById<ImageView>(R.id.type_poison_img)
//        val imgTypeGround = dialog.findViewById<ImageView>(R.id.type_ground_img)
//        val imgTypeFlying = dialog.findViewById<ImageView>(R.id.type_flying_img)
//        val imgTypePsychic = dialog.findViewById<ImageView>(R.id.type_psychic_img)
//        val imgTypeRock = dialog.findViewById<ImageView>(R.id.type_rock_img)
//        val imgTypeGhost = dialog.findViewById<ImageView>(R.id.type_ghost_img)
//        val imgTypeDragon = dialog.findViewById<ImageView>(R.id.type_dragon_img)
//        val imgTypeDark = dialog.findViewById<ImageView>(R.id.type_dark_img)
//        val imgTypeSteel = dialog.findViewById<ImageView>(R.id.type_steel_img)
//        val imgTypeFairy = dialog.findViewById<ImageView>(R.id.type_fairy_img)
//
//
//        if (cardTypeBug != null && imgTypeBug != null) {
//            if (filtersTypesList.contains("bug")) {
//                activeFilterInTypesOrWeaknesses("bug", cardTypeBug, imgTypeBug)
//                buttonAction("bug", cardTypeBug, false, imgTypeBug, filtersTypesList)
//            } else {
//                buttonAction("bug", cardTypeBug, true, imgTypeBug, filtersTypesList)
//            }
//        }
//
//        if (cardTypeNormal != null && imgTypeNormal != null) {
//            if (filtersTypesList.contains("normal")) {
//                activeFilterInTypesOrWeaknesses("normal", cardTypeNormal, imgTypeNormal)
//                buttonAction(
//                    "normal",
//                    cardTypeNormal,
//                    false,
//                    imgTypeNormal,
//                    filtersTypesList
//                )
//            } else {
//                buttonAction(
//                    "normal",
//                    cardTypeNormal,
//                    true,
//                    imgTypeNormal,
//                    filtersTypesList
//                )
//            }
//        }
//
//        if (cardTypeFire != null && imgTypeFire != null) {
//            if (filtersTypesList.contains("fire")) {
//                activeFilterInTypesOrWeaknesses("fire", cardTypeFire, imgTypeFire)
//                buttonAction("fire", cardTypeFire, false, imgTypeFire, filtersTypesList)
//            } else {
//                buttonAction("fire", cardTypeFire, true, imgTypeFire, filtersTypesList)
//            }
//        }
//
//        if (cardTypeWater != null && imgTypeWater != null) {
//            if (filtersTypesList.contains("water")) {
//                activeFilterInTypesOrWeaknesses("water", cardTypeWater, imgTypeWater)
//                buttonAction("water", cardTypeWater, false, imgTypeWater, filtersTypesList)
//            } else {
//                buttonAction("water", cardTypeWater, true, imgTypeWater, filtersTypesList)
//            }
//        }
//
//        if (cardTypeEletric != null && imgTypeEletric != null) {
//            if (filtersTypesList.contains("electric")) {
//                activeFilterInTypesOrWeaknesses("electric", cardTypeEletric, imgTypeEletric)
//                buttonAction(
//                    "electric",
//                    cardTypeEletric,
//                    false,
//                    imgTypeEletric,
//                    filtersTypesList
//                )
//            } else {
//                buttonAction(
//                    "electric",
//                    cardTypeEletric,
//                    true,
//                    imgTypeEletric,
//                    filtersTypesList
//                )
//            }
//
//        }
//
//        if (cardTypeGrass != null && imgTypeGrass != null) {
//            if (filtersTypesList.contains("grass")) {
//                activeFilterInTypesOrWeaknesses("grass", cardTypeGrass, imgTypeGrass)
//                buttonAction("grass", cardTypeGrass, false, imgTypeGrass, filtersTypesList)
//            } else {
//                buttonAction("grass", cardTypeGrass, true, imgTypeGrass, filtersTypesList)
//            }
//
//        }
//
//        if (cardTypeIce != null && imgTypeIce != null) {
//            if (filtersTypesList.contains("ice")) {
//                activeFilterInTypesOrWeaknesses("ice", cardTypeIce, imgTypeIce)
//                buttonAction("ice", cardTypeIce, false, imgTypeIce, filtersTypesList)
//            } else {
//                buttonAction("ice", cardTypeIce, true, imgTypeIce, filtersTypesList)
//            }
//
//        }
//
//        if (cardTypeFighting != null && imgTypeFighting != null) {
//            if (filtersTypesList.contains("fighting")) {
//                activeFilterInTypesOrWeaknesses(
//                    "fighting",
//                    cardTypeFighting,
//                    imgTypeFighting
//                )
//                buttonAction(
//                    "fighting",
//                    cardTypeFighting,
//                    false,
//                    imgTypeFighting,
//                    filtersTypesList
//                )
//            } else {
//                buttonAction(
//                    "fighting",
//                    cardTypeFighting,
//                    true,
//                    imgTypeFighting,
//                    filtersTypesList
//                )
//            }
//
//        }
//
//        if (cardTypePoison != null && imgTypePoison != null) {
//            if (filtersTypesList.contains("poison")) {
//                activeFilterInTypesOrWeaknesses("poison", cardTypePoison, imgTypePoison)
//                buttonAction(
//                    "poison",
//                    cardTypePoison,
//                    false,
//                    imgTypePoison,
//                    filtersTypesList
//                )
//            } else {
//                buttonAction(
//                    "poison",
//                    cardTypePoison,
//                    true,
//                    imgTypePoison,
//                    filtersTypesList
//                )
//            }
//
//        }
//
//        if (cardTypeGround != null && imgTypeGround != null) {
//            if (filtersTypesList.contains("ground")) {
//                activeFilterInTypesOrWeaknesses("ground", cardTypeGround, imgTypeGround)
//                buttonAction(
//                    "ground",
//                    cardTypeGround,
//                    false,
//                    imgTypeGround,
//                    filtersTypesList
//                )
//            } else {
//                buttonAction(
//                    "ground",
//                    cardTypeGround,
//                    true,
//                    imgTypeGround,
//                    filtersTypesList
//                )
//            }
//
//        }
//
//        if (cardTypeFlying != null && imgTypeFlying != null) {
//            if (filtersTypesList.contains("flying")) {
//                activeFilterInTypesOrWeaknesses("flying", cardTypeFlying, imgTypeFlying)
//                buttonAction(
//                    "flying",
//                    cardTypeFlying,
//                    false,
//                    imgTypeFlying,
//                    filtersTypesList
//                )
//            } else {
//                buttonAction(
//                    "flying",
//                    cardTypeFlying,
//                    true,
//                    imgTypeFlying,
//                    filtersTypesList
//                )
//            }
//
//        }
//
//        if (cardTypePsychic != null && imgTypePsychic != null) {
//            if (filtersTypesList.contains("psychic")) {
//                activeFilterInTypesOrWeaknesses("psychic", cardTypePsychic, imgTypePsychic)
//                buttonAction(
//                    "psychic",
//                    cardTypePsychic,
//                    false,
//                    imgTypePsychic,
//                    filtersTypesList
//                )
//            } else {
//                buttonAction(
//                    "psychic",
//                    cardTypePsychic,
//                    true,
//                    imgTypePsychic,
//                    filtersTypesList
//                )
//            }
//
//        }
//
//        if (cardTypeRock != null && imgTypeRock != null) {
//            if (filtersTypesList.contains("rock")) {
//                activeFilterInTypesOrWeaknesses("rock", cardTypeRock, imgTypeRock)
//                buttonAction("rock", cardTypeRock, false, imgTypeRock, filtersTypesList)
//            } else {
//                buttonAction("rock", cardTypeRock, true, imgTypeRock, filtersTypesList)
//            }
//
//        }
//
//        if (cardTypeGhost != null && imgTypeGhost != null) {
//            if (filtersTypesList.contains("ghost")) {
//                activeFilterInTypesOrWeaknesses("ghost", cardTypeGhost, imgTypeGhost)
//                buttonAction("ghost", cardTypeGhost, false, imgTypeGhost, filtersTypesList)
//            } else {
//                buttonAction("ghost", cardTypeGhost, true, imgTypeGhost, filtersTypesList)
//            }
//
//        }
//
//        if (cardTypeDragon != null && imgTypeDragon != null) {
//            if (filtersTypesList.contains("dragon")) {
//                activeFilterInTypesOrWeaknesses("dragon", cardTypeDragon, imgTypeDragon)
//                buttonAction(
//                    "dragon",
//                    cardTypeDragon,
//                    false,
//                    imgTypeDragon,
//                    filtersTypesList
//                )
//            } else {
//                buttonAction(
//                    "dragon",
//                    cardTypeDragon,
//                    true,
//                    imgTypeDragon,
//                    filtersTypesList
//                )
//            }
//
//        }
//
//        if (cardTypeDark != null && imgTypeDark != null) {
//            if (filtersTypesList.contains("dark")) {
//                activeFilterInTypesOrWeaknesses("dark", cardTypeDark, imgTypeDark)
//                buttonAction("dark", cardTypeDark, false, imgTypeDark, filtersTypesList)
//            } else {
//                buttonAction("dark", cardTypeDark, true, imgTypeDark, filtersTypesList)
//            }
//
//        }
//
//        if (cardTypeSteel != null && imgTypeSteel != null) {
//            if (filtersTypesList.contains("steel")) {
//                activeFilterInTypesOrWeaknesses("steel", cardTypeSteel, imgTypeSteel)
//                buttonAction("steel", cardTypeSteel, false, imgTypeSteel, filtersTypesList)
//            } else {
//                buttonAction("steel", cardTypeSteel, true, imgTypeSteel, filtersTypesList)
//            }
//
//        }
//
//        if (cardTypeFairy != null && imgTypeFairy != null) {
//            if (filtersTypesList.contains("fairy")) {
//                activeFilterInTypesOrWeaknesses("fairy", cardTypeFairy, imgTypeFairy)
//                buttonAction("fairy", cardTypeFairy, false, imgTypeFairy, filtersTypesList)
//            } else {
//                buttonAction("fairy", cardTypeFairy, true, imgTypeFairy, filtersTypesList)
//            }
//        }
//
//        val btnApply = dialog.findViewById<Button>(R.id.btn_types_apply)
//
//
//        btnApply?.setOnClickListener {
//            filterArrayList.clear()
//
//            if (filtersTypesList.isNotEmpty() && filterArrayList.isEmpty()) {
//                for (tipos in tempArrayList.indices) {
//                    if (tempArrayList[tipos].types.size > 1) {
//                        if (filtersTypesList.size > 0) {
//                            for (filterTypes in filtersTypesList) {
//                                if (tempArrayList[tipos].types[0].name.contains(filterTypes) || tempArrayList[tipos].types[1].name.contains(
//                                        filterTypes
//                                    )
//                                ) {
//                                    filterArrayList.add(tempArrayList[tipos])
//                                }
//                            }
//                        }
//
//                    } else {
//                        if (filtersTypesList.size > 0) {
//                            for (filterTypes in filtersTypesList) {
//                                if (tempArrayList[tipos].types[0].name.contains(filterTypes)) {
//                                    filterArrayList.add(tempArrayList[tipos])
//                                }
//                            }
//                        } else {
//                            dialog.dismiss()
//                        }
//                    }
//                }
//                val unic = filterArrayList.distinct()
//                adapterInRecyclerView(unic)
//            } else if (filtersTypesList.isNotEmpty() && filterArrayList.isNotEmpty()) {
//                for (tipos in filterArrayList.indices) {
//                    if (filterArrayList[tipos].types.size > 1) {
//                        if (filtersTypesList.size > 0) {
//                            for (filterTypes in filtersTypesList) {
//                                if (filterArrayList[tipos].types[0].name.contains(
//                                        filterTypes
//                                    ) || filterArrayList[tipos].types[1].name.contains(
//                                        filterTypes
//                                    )
//                                ) {
//                                    filterCloneTempArrayList.add(filterArrayList[tipos])
//                                }
//                            }
//                        }
//
//                    } else {
//                        if (filtersTypesList.size > 0) {
//                            for (filterTypes in filtersTypesList) {
//                                if (filterArrayList[tipos].types[0].name.contains(
//                                        filterTypes
//                                    )
//                                ) {
//                                    filterCloneTempArrayList.add(filterArrayList[tipos])
//                                }
//                            }
//                        } else {
//                            dialog.dismiss()
//                        }
//                    }
//                }
//                val unic = filterCloneTempArrayList.distinct()
//                adapterInRecyclerView(unic)
//            } else {
//                adapterInRecyclerView(tempArrayList)
//            }
//
//            if (filtersWeightsList.isNotEmpty() && filterArrayList.isEmpty()) {
//                if (filtersWeightsList.size == 1) {
//                    if (filtersWeightsList.contains("light")) {
//                        Log.i("PESO", "showFiltersAdvanced: pokemons leves")
//                        filterArrayList.clear()
//                        for (pesoIndice in tempArrayList.indices) {
//                            if (tempArrayList[pesoIndice].weight.toInt() <= 500) {
//                                filterArrayList.add(tempArrayList[pesoIndice])
//                                Log.i("PESO", "peso: ${filterArrayList.size}")
//                            }
//                        }
//                        adapterInRecyclerView(filterArrayList)
//                    } else if (filtersWeightsList.contains("pnormal")) {
//                        Log.i("PESO", "showFiltersAdvanced: pokemons normais")
//                        filterArrayList.clear()
//                        for (pesoIndice in tempArrayList.indices) {
//                            if (tempArrayList[pesoIndice].weight.toInt() > 500 && tempArrayList[pesoIndice].weight.toInt() <= 1250) {
//                                filterArrayList.add(tempArrayList[pesoIndice])
//                                Log.i("PESO", "peso: ${filterArrayList.size}")
//                            }
//                        }
//                        adapterInRecyclerView(filterArrayList)
//                    } else {
//                        Log.i("PESO", "showFiltersAdvanced: pokemons pesados")
//                        filterArrayList.clear()
//                        for (pesoIndice in tempArrayList.indices) {
//                            if (tempArrayList[pesoIndice].weight.toInt() > 1250) {
//                                filterArrayList.add(tempArrayList[pesoIndice])
//                                Log.i("PESO", "peso: ${filterArrayList.size}")
//                            }
//                        }
//                        adapterInRecyclerView(filterArrayList)
//                    }
//                } else if (filtersWeightsList.size == 2) {
//                    if (filtersWeightsList.contains("light") && filtersWeightsList.contains(
//                            "pnormal"
//                        )
//                    ) {
//                        Log.i("PESO", "showFiltersAdvanced: pokemons leves e normais")
//                        filterArrayList.clear()
//                        for (pesoIndice in tempArrayList.indices) {
//                            if (tempArrayList[pesoIndice].weight.toInt() <= 1250) {
//                                filterArrayList.add(tempArrayList[pesoIndice])
//                                Log.i("PESO", "peso: ${filterArrayList.size}")
//                            }
//                        }
//                        adapterInRecyclerView(filterArrayList)
//                    } else if (filtersWeightsList.contains("light") && filtersWeightsList.contains(
//                            "heavy"
//                        )
//                    ) {
//                        Log.i("PESO", "showFiltersAdvanced: pokemons leves e pesados")
//                        filterArrayList.clear()
//                        for (pesoIndice in tempArrayList.indices) {
//                            if (tempArrayList[pesoIndice].weight.toInt() < 500 || tempArrayList[pesoIndice].weight.toInt() > 1250) {
//                                filterArrayList.add(tempArrayList[pesoIndice])
//                                Log.i("PESO", "peso: ${filterArrayList.size}")
//                            }
//                        }
//                        adapterInRecyclerView(filterArrayList)
//                    } else if (filtersWeightsList.contains("pnormal") && filtersWeightsList.contains(
//                            "heavy"
//                        )
//                    ) {
//                        Log.i("PESO", "showFiltersAdvanced: pokemons normais e pesados")
//                        filterArrayList.clear()
//                        for (pesoIndice in tempArrayList.indices) {
//                            if (tempArrayList[pesoIndice].weight.toInt() > 500) {
//                                filterArrayList.add(tempArrayList[pesoIndice])
//                                Log.i("PESO", "peso: ${filterArrayList.size}")
//                            }
//                        }
//                        adapterInRecyclerView(filterArrayList)
//                    }
//                } else {
//                    Log.i("PESO", "showFiltersAdvanced: pokemons leves e normais, pesados")
//                    filterArrayList.clear()
//                    adapterInRecyclerView(tempArrayList)
//                }
//
//            } else if (filtersWeightsList.isNotEmpty() && filterArrayList.isNotEmpty()) {
//                if (filtersWeightsList.size == 1) {
//                    if (filtersWeightsList.contains("light")) {
//                        Log.i("PESO", "showFiltersAdvanced: pokemons leves")
//                        filterCloneTempArrayList.clear()
//                        for (pesoIndice in filterArrayList.indices) {
//                            if (filterArrayList[pesoIndice].weight.toInt() <= 500) {
//                                filterCloneTempArrayList.add(filterArrayList[pesoIndice])
//                                Log.i("PESO", "peso: ${filterCloneTempArrayList.size}")
//                            }
//                        }
//                        adapterInRecyclerView(filterCloneTempArrayList)
//                    } else if (filtersWeightsList.contains("pnormal")) {
//                        Log.i("PESO", "showFiltersAdvanced: pokemons normais")
//                        filterCloneTempArrayList.clear()
//                        for (pesoIndice in filterArrayList.indices) {
//                            if (filterArrayList[pesoIndice].weight.toInt() > 500 && filterArrayList[pesoIndice].weight.toInt() <= 1250) {
//                                filterCloneTempArrayList.add(filterArrayList[pesoIndice])
//                                Log.i("PESO", "peso: ${filterCloneTempArrayList.size}")
//                            }
//                        }
//                        adapterInRecyclerView(filterCloneTempArrayList)
//                    } else {
//                        Log.i("PESO", "showFiltersAdvanced: pokemons pesados")
//                        filterCloneTempArrayList.clear()
//                        for (pesoIndice in filterArrayList.indices) {
//                            if (filterArrayList[pesoIndice].weight.toInt() > 1250) {
//                                filterCloneTempArrayList.add(filterArrayList[pesoIndice])
//                                Log.i("PESO", "peso: ${filterCloneTempArrayList.size}")
//                            }
//                        }
//                        adapterInRecyclerView(filterCloneTempArrayList)
//                    }
//                } else if (filtersWeightsList.size == 2) {
//                    if (filtersWeightsList.contains("light") && filtersWeightsList.contains(
//                            "pnormal"
//                        )
//                    ) {
//                        Log.i("PESO", "showFiltersAdvanced: pokemons leves e normais")
//                        filterCloneTempArrayList.clear()
//                        for (pesoIndice in filterArrayList.indices) {
//                            if (filterArrayList[pesoIndice].weight.toInt() <= 1250) {
//                                filterCloneTempArrayList.add(filterArrayList[pesoIndice])
//                                Log.i("PESO", "peso: ${filterCloneTempArrayList.size}")
//                            }
//                        }
//                        adapterInRecyclerView(filterCloneTempArrayList)
//                    } else if (filtersWeightsList.contains("light") && filtersWeightsList.contains(
//                            "heavy"
//                        )
//                    ) {
//                        Log.i("PESO", "showFiltersAdvanced: pokemons leves e pesados")
//                        filterCloneTempArrayList.clear()
//                        for (pesoIndice in filterArrayList.indices) {
//                            if (filterArrayList[pesoIndice].weight.toInt() < 500 || filterArrayList[pesoIndice].weight.toInt() > 1250) {
//                                filterCloneTempArrayList.add(filterArrayList[pesoIndice])
//                                Log.i("PESO", "peso: ${filterCloneTempArrayList.size}")
//                            }
//                        }
//                        adapterInRecyclerView(filterCloneTempArrayList)
//                    } else if (filtersWeightsList.contains("pnormal") && filtersWeightsList.contains(
//                            "heavy"
//                        )
//                    ) {
//                        Log.i("PESO", "showFiltersAdvanced: pokemons normais e pesados")
//                        filterCloneTempArrayList.clear()
//                        for (pesoIndice in filterArrayList.indices) {
//                            if (filterArrayList[pesoIndice].weight.toInt() > 500) {
//                                filterCloneTempArrayList.add(filterArrayList[pesoIndice])
//                                Log.i("PESO", "peso: ${filterCloneTempArrayList.size}")
//                            }
//                        }
//                        adapterInRecyclerView(filterCloneTempArrayList)
//                    }
//                } else {
//                    filterCloneTempArrayList.clear()
//                    adapterInRecyclerView(filterArrayList)
//                }
//            }
//
//            if (filtersHeightsList.isNotEmpty() && filterArrayList.isEmpty()) {
//
//                if (filtersHeightsList.size == 1) {
//                    if (filtersHeightsList.contains("short")) {
//                        filterArrayList.clear()
//                        for (i in tempArrayList.indices) {
//                            if (tempArrayList[i].height.toInt() <= 7) {
//                                filterArrayList.add(tempArrayList[i])
//                            }
//                        }
//                        adapterInRecyclerView(filterArrayList)
//                    } else if (filtersHeightsList.contains("medium")) {
//                        filterArrayList.clear()
//                        for (i in tempArrayList.indices) {
//                            if (tempArrayList[i].height.toInt() > 7 && tempArrayList[i].height.toInt() <= 14) {
//                                filterArrayList.add(tempArrayList[i])
//                            }
//                        }
//                        adapterInRecyclerView(filterArrayList)
//                    } else if (filtersHeightsList.contains("tall")) {
//                        filterArrayList.clear()
//                        for (i in tempArrayList.indices) {
//                            if (tempArrayList[i].height.toInt() > 14) {
//                                filterArrayList.add(tempArrayList[i])
//                            }
//                        }
//                        adapterInRecyclerView(filterArrayList)
//                    }
//                } else if (filtersHeightsList.size == 2) {
//                    if (filtersHeightsList.contains("short") && filtersHeightsList.contains(
//                            "medium"
//                        )
//                    ) {
//                        filterArrayList.clear()
//                        for (i in tempArrayList.indices) {
//                            if (tempArrayList[i].height.toInt() <= 14) {
//                                filterArrayList.add(tempArrayList[i])
//                            }
//                        }
//                        adapterInRecyclerView(filterArrayList)
//                    } else if (filtersHeightsList.contains("medium") && filtersHeightsList.contains(
//                            "tall"
//                        )
//                    ) {
//                        filterArrayList.clear()
//                        for (i in tempArrayList.indices) {
//                            if (tempArrayList[i].height.toInt() > 7) {
//                                filterArrayList.add(tempArrayList[i])
//                            }
//                        }
//                        adapterInRecyclerView(filterArrayList)
//                    } else if (filtersHeightsList.contains("short") && filtersHeightsList.contains(
//                            "tall"
//                        )
//                    ) {
//                        filterArrayList.clear()
//                        for (i in tempArrayList.indices) {
//                            if (tempArrayList[i].height.toInt() < 7 || tempArrayList[i].height.toInt() > 14) {
//                                filterArrayList.add(tempArrayList[i])
//                            }
//                        }
//                        adapterInRecyclerView(filterArrayList)
//                    }
//                } else {
//                    filterArrayList.clear()
//                    adapterInRecyclerView(tempArrayList)
//                }
//
//            } else if (filtersHeightsList.isNotEmpty() && filterArrayList.isNotEmpty()) {
//                if (filtersHeightsList.size == 1) {
//                    if (filtersHeightsList.contains("short")) {
//                        filterCloneTempArrayList.clear()
//                        for (i in filterArrayList.indices) {
//                            if (filterArrayList[i].height.toInt() <= 7) {
//                                filterCloneTempArrayList.add(filterArrayList[i])
//                            }
//                        }
//                        adapterInRecyclerView(filterCloneTempArrayList)
//                    } else if (filtersHeightsList.contains("medium")) {
//                        filterCloneTempArrayList.clear()
//                        for (i in filterArrayList.indices) {
//                            if (filterArrayList[i].height.toInt() > 7 && filterArrayList[i].height.toInt() <= 14) {
//                                filterCloneTempArrayList.add(filterArrayList[i])
//                            }
//                        }
//                        adapterInRecyclerView(filterCloneTempArrayList)
//                    } else if (filtersHeightsList.contains("tall")) {
//                        filterCloneTempArrayList.clear()
//                        for (i in filterArrayList.indices) {
//                            if (filterArrayList[i].height.toInt() > 14) {
//                                filterCloneTempArrayList.add(filterArrayList[i])
//                            }
//                        }
//                        adapterInRecyclerView(filterCloneTempArrayList)
//                    }
//                } else if (filtersHeightsList.size == 2) {
//                    if (filtersHeightsList.contains("short") && filtersHeightsList.contains(
//                            "medium"
//                        )
//                    ) {
//                        filterCloneTempArrayList.clear()
//                        for (i in filterArrayList.indices) {
//                            if (filterArrayList[i].height.toInt() <= 14) {
//                                filterCloneTempArrayList.add(filterArrayList[i])
//                            }
//                        }
//                        adapterInRecyclerView(filterCloneTempArrayList)
//                    } else if (filtersHeightsList.contains("medium") && filtersHeightsList.contains(
//                            "tall"
//                        )
//                    ) {
//                        filterCloneTempArrayList.clear()
//                        for (i in filterArrayList.indices) {
//                            if (filterArrayList[i].height.toInt() > 7) {
//                                filterCloneTempArrayList.add(filterArrayList[i])
//                            }
//                        }
//                        adapterInRecyclerView(filterCloneTempArrayList)
//                    } else if (filtersHeightsList.contains("short") && filtersHeightsList.contains(
//                            "tall"
//                        )
//                    ) {
//                        filterCloneTempArrayList.clear()
//                        for (i in filterArrayList.indices) {
//                            if (filterArrayList[i].height.toInt() < 7 || filterArrayList[i].height.toInt() > 14) {
//                                filterCloneTempArrayList.add(filterArrayList[i])
//                            }
//                        }
//                        adapterInRecyclerView(filterCloneTempArrayList)
//                    }
//                } else {
//                    filterCloneTempArrayList.clear()
//                    adapterInRecyclerView(filterArrayList)
//                }
//            }
//
//            if (filtersRangerList.size == 2) {
//                for (posicao in filtersRangerList[0]..filtersRangerList[1]) {
//                    filterArrayList.add(tempArrayList[posicao])
//                }
//
//                adapterInRecyclerView(filterArrayList)
//
//                Log.i("RANGESLIDER", "onStopTrackingTouch: ${filterArrayList.size}")
//            }
//
//            if (filtersWeaknessesList.isNotEmpty() && filterArrayList.isEmpty()) {
//
//                if (filtersWeaknessesList.size > 0) {
//                    val filters: ArrayList<String> = ArrayList()
//                    for (i in filtersWeaknessesList) {
//                        val weak = weaknessPokemon(i)
//                        for (k in weak) {
//                            filters.add(k)
//                        }
//                    }
//
//                    val filtros = filters.distinct()
//                    for (tipos in tempArrayList.indices) {
//                        if (tempArrayList[tipos].types.size > 1) {
//                            if (filtros.size > 0) {
//                                for (filterTypes in filtros) {
//                                    if (tempArrayList[tipos].types[0].name.contains(
//                                            filterTypes
//                                        ) || tempArrayList[tipos].types[1].name.contains(
//                                            filterTypes
//                                        )
//                                    ) {
//                                        filterArrayList.add(tempArrayList[tipos])
//                                    }
//                                }
//                            }
//
//                        } else {
//                            if (filtros.size > 0) {
//                                for (filterTypes in filtros) {
//                                    if (tempArrayList[tipos].types[0].name.contains(
//                                            filterTypes
//                                        )
//                                    ) {
//                                        filterArrayList.add(tempArrayList[tipos])
//                                    }
//                                }
//                            } else {
//                                dialog.dismiss()
//                            }
//                        }
//                    }
//                    val unic = filterArrayList.distinct()
//                    adapterInRecyclerView(unic)
//                }
//            } else if (filtersWeaknessesList.isNotEmpty() && filterArrayList.isNotEmpty()) {
//                if (filtersWeaknessesList.size > 0) {
//                    val filters: ArrayList<String> = ArrayList()
//                    for (i in filtersWeaknessesList) {
//                        val weak = weaknessPokemon(i)
//                        for (k in weak) {
//                            filters.add(k)
//                        }
//                    }
//
//                    val filtros = filters.distinct()
//                    for (tipos in filterArrayList.indices) {
//                        if (filterArrayList[tipos].types.size > 1) {
//                            if (filtros.size > 0) {
//                                for (filterTypes in filtros) {
//                                    if (filterArrayList[tipos].types[0].name.contains(
//                                            filterTypes
//                                        ) || filterArrayList[tipos].types[1].name.contains(
//                                            filterTypes
//                                        )
//                                    ) {
//                                        filterCloneTempArrayList.add(filterArrayList[tipos])
//                                    }
//                                }
//                            }
//
//                        } else {
//                            if (filtros.size > 0) {
//                                for (filterTypes in filtros) {
//                                    if (filterArrayList[tipos].types[0].name.contains(
//                                            filterTypes
//                                        )
//                                    ) {
//                                        filterCloneTempArrayList.add(filterArrayList[tipos])
//                                    }
//                                }
//                            } else {
//                                dialog.dismiss()
//                            }
//                        }
//                    }
//                    val unic = filterCloneTempArrayList.distinct()
//                    adapterInRecyclerView(unic)
//                }
//            }
//            dialog.dismiss()
//        }
//
//        val btnReset = dialog.findViewById<Button>(R.id.btn_types_reset)
//
//        btnReset?.setOnClickListener {
//            if (filtersTypesList.isNotEmpty() || filtersWeaknessesList.isNotEmpty() || filtersWeightsList.isNotEmpty() || filtersHeightsList.isNotEmpty() || filtersRangerList.isNotEmpty()) {
//                filtersTypesList.clear()
//                filtersWeaknessesList.clear()
//                filterArrayList.clear()
//                filtersHeightsList.clear()
//                filtersRangerList.clear()
//                filtersWeightsList.clear()
//                adapterInRecyclerView(tempArrayList)
//            }
//            dialog.dismiss()
//        }
//
//        val cardWeaknessesBug =
//            dialog.findViewById<MaterialCardView>(R.id.card_weaknesses_filter_bug)
//        val cardWeaknessesNormal =
//            dialog.findViewById<MaterialCardView>(R.id.card_weaknesses_filter_normal)
//        val cardWeaknessesFire =
//            dialog.findViewById<MaterialCardView>(R.id.card_weaknesses_filter_fire)
//        val cardWeaknessesWater =
//            dialog.findViewById<MaterialCardView>(R.id.card_weaknesses_filter_water)
//        val cardWeaknessesEletric =
//            dialog.findViewById<MaterialCardView>(R.id.card_weaknesses_filter_eletric)
//        val cardWeaknessesGrass =
//            dialog.findViewById<MaterialCardView>(R.id.card_weaknesses_filter_grass)
//        val cardWeaknessesIce =
//            dialog.findViewById<MaterialCardView>(R.id.card_weaknesses_filter_ice)
//        val cardWeaknessesFighting =
//            dialog.findViewById<MaterialCardView>(R.id.card_weaknesses_filter_fighting)
//        val cardWeaknessesPoison =
//            dialog.findViewById<MaterialCardView>(R.id.card_weaknesses_filter_poison)
//        val cardWeaknessesGround =
//            dialog.findViewById<MaterialCardView>(R.id.card_weaknesses_filter_ground)
//        val cardWeaknessesFlying =
//            dialog.findViewById<MaterialCardView>(R.id.card_weaknesses_filter_flying)
//        val cardWeaknessesPsychic =
//            dialog.findViewById<MaterialCardView>(R.id.card_weaknesses_filter_psychic)
//        val cardWeaknessesRock =
//            dialog.findViewById<MaterialCardView>(R.id.card_weaknesses_filter_rock)
//        val cardWeaknessesGhost =
//            dialog.findViewById<MaterialCardView>(R.id.card_weaknesses_filter_ghost)
//        val cardWeaknessesDragon =
//            dialog.findViewById<MaterialCardView>(R.id.card_weaknesses_filter_dragon)
//        val cardWeaknessesDark =
//            dialog.findViewById<MaterialCardView>(R.id.card_weaknesses_filter_dark)
//        val cardWeaknessesSteel =
//            dialog.findViewById<MaterialCardView>(R.id.card_weaknesses_filter_steel)
//        val cardWeaknessesFairy =
//            dialog.findViewById<MaterialCardView>(R.id.card_weaknesses_filter_fairy)
//
//        val imgWeaknessesNormal = dialog.findViewById<ImageView>(R.id.weaknesses_normal_img)
//        val imgWeaknessesBug = dialog.findViewById<ImageView>(R.id.weaknesses_bug_img)
//        val imgWeaknessesFire = dialog.findViewById<ImageView>(R.id.weaknesses_fire_img)
//        val imgWeaknessesWater = dialog.findViewById<ImageView>(R.id.weaknesses_water_img)
//        val imgWeaknessesEletric =
//            dialog.findViewById<ImageView>(R.id.weaknesses_eletric_img)
//        val imgWeaknessesGrass = dialog.findViewById<ImageView>(R.id.weaknesses_grass_img)
//        val imgWeaknessesIce = dialog.findViewById<ImageView>(R.id.weaknesses_ice_img)
//        val imgWeaknessesFighting =
//            dialog.findViewById<ImageView>(R.id.weaknesses_fighting_img)
//        val imgWeaknessesPoison = dialog.findViewById<ImageView>(R.id.weaknesses_poison_img)
//        val imgWeaknessesGround = dialog.findViewById<ImageView>(R.id.weaknesses_ground_img)
//        val imgWeaknessesFlying = dialog.findViewById<ImageView>(R.id.weaknesses_flying_img)
//        val imgWeaknessesPsychic =
//            dialog.findViewById<ImageView>(R.id.weaknesses_psychic_img)
//        val imgWeaknessesRock = dialog.findViewById<ImageView>(R.id.weaknesses_rock_img)
//        val imgWeaknessesGhost = dialog.findViewById<ImageView>(R.id.weaknesses_ghost_img)
//        val imgWeaknessesDragon = dialog.findViewById<ImageView>(R.id.weaknesses_dragon_img)
//        val imgWeaknessesDark = dialog.findViewById<ImageView>(R.id.weaknesses_dark_img)
//        val imgWeaknessesSteel = dialog.findViewById<ImageView>(R.id.weaknesses_steel_img)
//        val imgWeaknessesFairy = dialog.findViewById<ImageView>(R.id.weaknesses_fairy_img)
//
//        if (cardWeaknessesBug != null && imgWeaknessesBug != null) {
//            if (filtersWeaknessesList.contains("bug")) {
//                activeFilterInTypesOrWeaknesses("bug", cardWeaknessesBug, imgWeaknessesBug)
//                buttonAction(
//                    "bug",
//                    cardWeaknessesBug,
//                    false,
//                    imgWeaknessesBug,
//                    filtersWeaknessesList
//                )
//            } else {
//                buttonAction(
//                    "bug",
//                    cardWeaknessesBug,
//                    true,
//                    imgWeaknessesBug,
//                    filtersWeaknessesList
//                )
//            }
//        }
//
//        if (cardWeaknessesNormal != null && imgWeaknessesNormal != null) {
//            if (filtersWeaknessesList.contains("normal")) {
//                activeFilterInTypesOrWeaknesses(
//                    "normal",
//                    cardWeaknessesNormal,
//                    imgWeaknessesNormal
//                )
//                buttonAction(
//                    "normal",
//                    cardWeaknessesNormal,
//                    false,
//                    imgWeaknessesNormal,
//                    filtersWeaknessesList
//                )
//            } else {
//                buttonAction(
//                    "normal",
//                    cardWeaknessesNormal,
//                    true,
//                    imgWeaknessesNormal,
//                    filtersWeaknessesList
//                )
//            }
//        }
//
//        if (cardWeaknessesFire != null && imgWeaknessesFire != null) {
//            if (filtersWeaknessesList.contains("fire")) {
//                activeFilterInTypesOrWeaknesses(
//                    "fire",
//                    cardWeaknessesFire,
//                    imgWeaknessesFire
//                )
//                buttonAction(
//                    "fire",
//                    cardWeaknessesFire,
//                    false,
//                    imgWeaknessesFire,
//                    filtersWeaknessesList
//                )
//            } else {
//                buttonAction(
//                    "fire",
//                    cardWeaknessesFire,
//                    true,
//                    imgWeaknessesFire,
//                    filtersWeaknessesList
//                )
//            }
//        }
//
//        if (cardWeaknessesWater != null && imgWeaknessesWater != null) {
//            if (filtersWeaknessesList.contains("water")) {
//                activeFilterInTypesOrWeaknesses(
//                    "water",
//                    cardWeaknessesWater,
//                    imgWeaknessesWater
//                )
//                buttonAction(
//                    "water",
//                    cardWeaknessesWater,
//                    false,
//                    imgWeaknessesWater,
//                    filtersWeaknessesList
//                )
//            } else {
//                buttonAction(
//                    "water",
//                    cardWeaknessesWater,
//                    true,
//                    imgWeaknessesWater,
//                    filtersWeaknessesList
//                )
//            }
//        }
//
//        if (cardWeaknessesEletric != null && imgWeaknessesEletric != null) {
//            if (filtersWeaknessesList.contains("electric")) {
//                activeFilterInTypesOrWeaknesses(
//                    "electric",
//                    cardWeaknessesEletric,
//                    imgWeaknessesEletric
//                )
//                buttonAction(
//                    "electric",
//                    cardWeaknessesEletric,
//                    false,
//                    imgWeaknessesEletric,
//                    filtersWeaknessesList
//                )
//            } else {
//                buttonAction(
//                    "electric",
//                    cardWeaknessesEletric,
//                    true,
//                    imgWeaknessesEletric,
//                    filtersWeaknessesList
//                )
//            }
//
//        }
//
//        if (cardWeaknessesGrass != null && imgWeaknessesGrass != null) {
//            if (filtersWeaknessesList.contains("grass")) {
//                activeFilterInTypesOrWeaknesses(
//                    "grass",
//                    cardWeaknessesGrass,
//                    imgWeaknessesGrass
//                )
//                buttonAction(
//                    "grass",
//                    cardWeaknessesGrass,
//                    false,
//                    imgWeaknessesGrass,
//                    filtersWeaknessesList
//                )
//            } else {
//                buttonAction(
//                    "grass",
//                    cardWeaknessesGrass,
//                    true,
//                    imgWeaknessesGrass,
//                    filtersWeaknessesList
//                )
//            }
//
//        }
//
//        if (cardWeaknessesIce != null && imgWeaknessesIce != null) {
//            if (filtersWeaknessesList.contains("ice")) {
//                activeFilterInTypesOrWeaknesses("ice", cardWeaknessesIce, imgWeaknessesIce)
//                buttonAction(
//                    "ice",
//                    cardWeaknessesIce,
//                    false,
//                    imgWeaknessesIce,
//                    filtersWeaknessesList
//                )
//            } else {
//                buttonAction(
//                    "ice",
//                    cardWeaknessesIce,
//                    true,
//                    imgWeaknessesIce,
//                    filtersWeaknessesList
//                )
//            }
//
//        }
//
//        if (cardWeaknessesFighting != null && imgWeaknessesFighting != null) {
//            if (filtersWeaknessesList.contains("fighting")) {
//                activeFilterInTypesOrWeaknesses(
//                    "fighting",
//                    cardWeaknessesFighting,
//                    imgWeaknessesFighting
//                )
//                buttonAction(
//                    "fighting",
//                    cardWeaknessesFighting,
//                    false,
//                    imgWeaknessesFighting,
//                    filtersWeaknessesList
//                )
//            } else {
//                buttonAction(
//                    "fighting",
//                    cardWeaknessesFighting,
//                    true,
//                    imgWeaknessesFighting,
//                    filtersWeaknessesList
//                )
//            }
//
//        }
//
//        if (cardWeaknessesPoison != null && imgWeaknessesPoison != null) {
//            if (filtersWeaknessesList.contains("poison")) {
//                activeFilterInTypesOrWeaknesses(
//                    "poison",
//                    cardWeaknessesPoison,
//                    imgWeaknessesPoison
//                )
//                buttonAction(
//                    "poison",
//                    cardWeaknessesPoison,
//                    false,
//                    imgWeaknessesPoison,
//                    filtersWeaknessesList
//                )
//            } else {
//                buttonAction(
//                    "poison",
//                    cardWeaknessesPoison,
//                    true,
//                    imgWeaknessesPoison,
//                    filtersWeaknessesList
//                )
//            }
//
//        }
//
//        if (cardWeaknessesGround != null && imgWeaknessesGround != null) {
//            if (filtersWeaknessesList.contains("ground")) {
//                activeFilterInTypesOrWeaknesses(
//                    "ground",
//                    cardWeaknessesGround,
//                    imgWeaknessesGround
//                )
//                buttonAction(
//                    "ground",
//                    cardWeaknessesGround,
//                    false,
//                    imgWeaknessesGround,
//                    filtersWeaknessesList
//                )
//            } else {
//                buttonAction(
//                    "ground",
//                    cardWeaknessesGround,
//                    true,
//                    imgWeaknessesGround,
//                    filtersWeaknessesList
//                )
//            }
//
//        }
//
//        if (cardWeaknessesFlying != null && imgWeaknessesFlying != null) {
//            if (filtersWeaknessesList.contains("flying")) {
//                activeFilterInTypesOrWeaknesses(
//                    "flying",
//                    cardWeaknessesFlying,
//                    imgWeaknessesFlying
//                )
//                buttonAction(
//                    "flying",
//                    cardWeaknessesFlying,
//                    false,
//                    imgWeaknessesFlying,
//                    filtersWeaknessesList
//                )
//            } else {
//                buttonAction(
//                    "flying",
//                    cardWeaknessesFlying,
//                    true,
//                    imgWeaknessesFlying,
//                    filtersWeaknessesList
//                )
//            }
//
//        }
//
//        if (cardWeaknessesPsychic != null && imgWeaknessesPsychic != null) {
//            if (filtersWeaknessesList.contains("psychic")) {
//                activeFilterInTypesOrWeaknesses(
//                    "psychic",
//                    cardWeaknessesPsychic,
//                    imgWeaknessesPsychic
//                )
//                buttonAction(
//                    "psychic",
//                    cardWeaknessesPsychic,
//                    false,
//                    imgWeaknessesPsychic,
//                    filtersWeaknessesList
//                )
//            } else {
//                buttonAction(
//                    "psychic",
//                    cardWeaknessesPsychic,
//                    true,
//                    imgWeaknessesPsychic,
//                    filtersWeaknessesList
//                )
//            }
//
//        }
//
//        if (cardWeaknessesRock != null && imgWeaknessesRock != null) {
//            if (filtersWeaknessesList.contains("rock")) {
//                activeFilterInTypesOrWeaknesses(
//                    "rock",
//                    cardWeaknessesRock,
//                    imgWeaknessesRock
//                )
//                buttonAction(
//                    "rock",
//                    cardWeaknessesRock,
//                    false,
//                    imgWeaknessesRock,
//                    filtersWeaknessesList
//                )
//            } else {
//                buttonAction(
//                    "rock",
//                    cardWeaknessesRock,
//                    true,
//                    imgWeaknessesRock,
//                    filtersWeaknessesList
//                )
//            }
//
//        }
//
//        if (cardWeaknessesGhost != null && imgWeaknessesGhost != null) {
//            if (filtersWeaknessesList.contains("ghost")) {
//                activeFilterInTypesOrWeaknesses(
//                    "ghost",
//                    cardWeaknessesGhost,
//                    imgWeaknessesGhost
//                )
//                buttonAction(
//                    "ghost",
//                    cardWeaknessesGhost,
//                    false,
//                    imgWeaknessesGhost,
//                    filtersWeaknessesList
//                )
//            } else {
//                buttonAction(
//                    "ghost",
//                    cardWeaknessesGhost,
//                    true,
//                    imgWeaknessesGhost,
//                    filtersWeaknessesList
//                )
//            }
//
//        }
//
//        if (cardWeaknessesDragon != null && imgWeaknessesDragon != null) {
//            if (filtersWeaknessesList.contains("dragon")) {
//                activeFilterInTypesOrWeaknesses(
//                    "dragon",
//                    cardWeaknessesDragon,
//                    imgWeaknessesDragon
//                )
//                buttonAction(
//                    "dragon",
//                    cardWeaknessesDragon,
//                    false,
//                    imgWeaknessesDragon,
//                    filtersWeaknessesList
//                )
//            } else {
//                buttonAction(
//                    "dragon",
//                    cardWeaknessesDragon,
//                    true,
//                    imgWeaknessesDragon,
//                    filtersWeaknessesList
//                )
//            }
//
//        }
//
//        if (cardWeaknessesDark != null && imgWeaknessesDark != null) {
//            if (filtersWeaknessesList.contains("dark")) {
//                activeFilterInTypesOrWeaknesses(
//                    "dark",
//                    cardWeaknessesDark,
//                    imgWeaknessesDark
//                )
//                buttonAction(
//                    "dark",
//                    cardWeaknessesDark,
//                    false,
//                    imgWeaknessesDark,
//                    filtersWeaknessesList
//                )
//            } else {
//                buttonAction(
//                    "dark",
//                    cardWeaknessesDark,
//                    true,
//                    imgWeaknessesDark,
//                    filtersWeaknessesList
//                )
//            }
//
//        }
//
//        if (cardWeaknessesSteel != null && imgWeaknessesSteel != null) {
//            if (filtersWeaknessesList.contains("steel")) {
//                activeFilterInTypesOrWeaknesses(
//                    "steel",
//                    cardWeaknessesSteel,
//                    imgWeaknessesSteel
//                )
//                buttonAction(
//                    "steel",
//                    cardWeaknessesSteel,
//                    false,
//                    imgWeaknessesSteel,
//                    filtersWeaknessesList
//                )
//            } else {
//                buttonAction(
//                    "steel",
//                    cardWeaknessesSteel,
//                    true,
//                    imgWeaknessesSteel,
//                    filtersWeaknessesList
//                )
//            }
//
//        }
//
//        if (cardWeaknessesFairy != null && imgWeaknessesFairy != null) {
//            if (filtersWeaknessesList.contains("fairy")) {
//                activeFilterInTypesOrWeaknesses(
//                    "fairy",
//                    cardWeaknessesFairy,
//                    imgWeaknessesFairy
//                )
//                buttonAction(
//                    "fairy",
//                    cardWeaknessesFairy,
//                    false,
//                    imgWeaknessesFairy,
//                    filtersWeaknessesList
//                )
//            } else {
//                buttonAction(
//                    "fairy",
//                    cardWeaknessesFairy,
//                    true,
//                    imgWeaknessesFairy,
//                    filtersWeaknessesList
//                )
//            }
//        }
//
//        val cardTypeHeightsShort =
//            dialog.findViewById<MaterialCardView>(R.id.cardTypeHeightsShort)
//        val cardTypeHeightsMedium =
//            dialog.findViewById<MaterialCardView>(R.id.cardTypeHeightsMedium)
//        val cardTypeHeightsTall =
//            dialog.findViewById<MaterialCardView>(R.id.cardTypeHeightsTall)
//
//        val imgTypeHeightsShort = dialog.findViewById<ImageView>(R.id.imgTypeHeightsShort)
//        val imgTypeHeightsMedium = dialog.findViewById<ImageView>(R.id.imgTypeHeightsMedium)
//        val imgTypeHeightsTall = dialog.findViewById<ImageView>(R.id.imgTypeHeightsTall)
//
//        if (cardTypeHeightsShort != null && imgTypeHeightsShort != null) {
//            if (filtersHeightsList.contains("short")) {
//                activeFilterInTypesOrWeaknesses(
//                    "short",
//                    cardTypeHeightsShort,
//                    imgTypeHeightsShort
//                )
//                buttonAction(
//                    "short",
//                    cardTypeHeightsShort,
//                    false,
//                    imgTypeHeightsShort,
//                    filtersHeightsList
//                )
//            } else {
//                buttonAction(
//                    "short",
//                    cardTypeHeightsShort,
//                    true,
//                    imgTypeHeightsShort,
//                    filtersHeightsList
//                )
//            }
//        }
//
//        if (cardTypeHeightsMedium != null && imgTypeHeightsMedium != null) {
//            if (filtersHeightsList.contains("medium")) {
//                activeFilterInTypesOrWeaknesses(
//                    "medium",
//                    cardTypeHeightsMedium,
//                    imgTypeHeightsMedium
//                )
//                buttonAction(
//                    "medium",
//                    cardTypeHeightsMedium,
//                    false,
//                    imgTypeHeightsMedium,
//                    filtersHeightsList
//                )
//            } else {
//                buttonAction(
//                    "medium",
//                    cardTypeHeightsMedium,
//                    true,
//                    imgTypeHeightsMedium,
//                    filtersHeightsList
//                )
//            }
//        }
//
//        if (cardTypeHeightsTall != null && imgTypeHeightsTall != null) {
//            if (filtersHeightsList.contains("tall")) {
//                activeFilterInTypesOrWeaknesses(
//                    "tall",
//                    cardTypeHeightsTall,
//                    imgTypeHeightsTall
//                )
//                buttonAction(
//                    "tall",
//                    cardTypeHeightsTall,
//                    false,
//                    imgTypeHeightsTall,
//                    filtersHeightsList
//                )
//            } else {
//                buttonAction(
//                    "tall",
//                    cardTypeHeightsTall,
//                    true,
//                    imgTypeHeightsTall,
//                    filtersHeightsList
//                )
//            }
//        }
//
//        val cardTypeWeightsNormal =
//            dialog.findViewById<MaterialCardView>(R.id.cardTypeWeightsNormal)
//        val cardTypeWeightsLight =
//            dialog.findViewById<MaterialCardView>(R.id.cardTypeWeightsLight)
//        val cardTypeWeightsHeavy =
//            dialog.findViewById<MaterialCardView>(R.id.cardTypeWeightsHeavy)
//
//        val imgTypeWeightsNormal = dialog.findViewById<ImageView>(R.id.imgTypeWeightsNormal)
//        val imgTypeWeightsLight = dialog.findViewById<ImageView>(R.id.imgTypeWeightsLight)
//        val imgTypeWeightsHeavy = dialog.findViewById<ImageView>(R.id.imgTypeWeightsHeavy)
//
//        if (cardTypeWeightsNormal != null && imgTypeWeightsNormal != null) {
//            if (filtersWeightsList.contains("pnormal")) {
//                activeFilterInTypesOrWeaknesses(
//                    "pnormal",
//                    cardTypeWeightsNormal,
//                    imgTypeWeightsNormal
//                )
//                buttonAction(
//                    "pnormal",
//                    cardTypeWeightsNormal,
//                    false,
//                    imgTypeWeightsNormal,
//                    filtersWeightsList
//                )
//            } else {
//                buttonAction(
//                    "pnormal",
//                    cardTypeWeightsNormal,
//                    true,
//                    imgTypeWeightsNormal,
//                    filtersWeightsList
//                )
//            }
//        }
//
//        if (cardTypeWeightsLight != null && imgTypeWeightsLight != null) {
//            if (filtersWeightsList.contains("light")) {
//                activeFilterInTypesOrWeaknesses(
//                    "light",
//                    cardTypeWeightsLight,
//                    imgTypeWeightsLight
//                )
//                buttonAction(
//                    "light",
//                    cardTypeWeightsLight,
//                    false,
//                    imgTypeWeightsLight,
//                    filtersWeightsList
//                )
//            } else {
//                buttonAction(
//                    "light",
//                    cardTypeWeightsLight,
//                    true,
//                    imgTypeWeightsLight,
//                    filtersWeightsList
//                )
//            }
//        }
//
//        if (cardTypeWeightsHeavy != null && imgTypeWeightsHeavy != null) {
//            if (filtersWeightsList.contains("heavy")) {
//                activeFilterInTypesOrWeaknesses(
//                    "heavy",
//                    cardTypeWeightsHeavy,
//                    imgTypeWeightsHeavy
//                )
//                buttonAction(
//                    "heavy",
//                    cardTypeWeightsHeavy,
//                    false,
//                    imgTypeWeightsHeavy,
//                    filtersWeightsList
//                )
//            } else {
//                buttonAction(
//                    "heavy",
//                    cardTypeWeightsHeavy,
//                    true,
//                    imgTypeWeightsHeavy,
//                    filtersWeightsList
//                )
//            }
//        }
//
//
//        val rangeSlider = dialog.findViewById<RangeSlider>(R.id.range_slider)
//
//        rangeSlider?.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
//
//            override fun onStartTrackingTouch(slider: RangeSlider) {}
//
//            override fun onStopTrackingTouch(slider: RangeSlider) {
//                val values = rangeSlider.values
//
//                if (filtersRangerList.size > 0) filtersRangerList.clear()
//
//                filtersRangerList.add(0, values[0].toInt())
//                filtersRangerList.add(1, values[1].toInt())
//
//                Log.i("RANGESLIDER", "onStopTrackingTouch: ${filtersRangerList}")
//
//            }
//
//
//        })
//
//    }

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

    private fun colorType(type: String): String = when (type) {
        "normal" -> "#818054"
        "fire" -> "#c25c10"
        "water" -> "#1d5ee9"
        "electric" -> "#FFEF00"
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
        "tall" -> "#2F4F4F"
        "medium" -> "#6A5ACD"
        "short" -> "#FF1493"
        "light" -> "#90EE90"
        "pnormal" -> "#6495ED"
        "heavy" -> "#778899"
        else -> type
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

        dialog.show()
    }


    private fun orderByNameZandA() {
        if (filterArrayList.isEmpty() && filterCloneTempArrayList.isEmpty()) {
            adapterInRecyclerView(tempArrayList.sortedByDescending { it.name })
        } else if (filterArrayList.isNotEmpty() && filterCloneTempArrayList.isEmpty()) {
            adapterInRecyclerView(filterArrayList.sortedByDescending { it.name })
        } else if (filterArrayList.isEmpty() && filterCloneTempArrayList.isNotEmpty()) {
            adapterInRecyclerView(filterCloneTempArrayList.sortedByDescending { it.name })
        }
    }

    private fun orderByNameAandZ() {
        if (filterArrayList.isEmpty() && filterCloneTempArrayList.isEmpty()) {
            adapterInRecyclerView(tempArrayList.sortedBy { it.name })
        } else if (filterArrayList.isNotEmpty() && filterCloneTempArrayList.isEmpty()) {
            adapterInRecyclerView(filterArrayList.sortedBy { it.name })
        } else if (filterArrayList.isEmpty() && filterCloneTempArrayList.isNotEmpty()) {
            adapterInRecyclerView(filterCloneTempArrayList.sortedBy { it.name })
        }
    }

    private fun highNumberPokemonFirst() {
        if (filterArrayList.isEmpty() && filterCloneTempArrayList.isEmpty()) {
            adapterInRecyclerView(tempArrayList.sortedByDescending { it.id.toInt() })
        } else if (filterArrayList.isNotEmpty() && filterCloneTempArrayList.isEmpty()) {
            adapterInRecyclerView(filterArrayList.sortedByDescending { it.id.toInt() })
        } else if (filterArrayList.isEmpty() && filterCloneTempArrayList.isNotEmpty()) {
            adapterInRecyclerView(filterCloneTempArrayList.sortedByDescending { it.id.toInt() })
        }
    }

    private fun smallNumberPokemonFirst() {
        if (filterArrayList.isEmpty() && filterCloneTempArrayList.isEmpty()) {
            adapterInRecyclerView(tempArrayList.sortedBy { it.id.toInt() })
        } else if (filterArrayList.isNotEmpty() && filterCloneTempArrayList.isEmpty()) {
            adapterInRecyclerView(filterArrayList.sortedBy { it.id.toInt() })
        } else if (filterArrayList.isEmpty() && filterCloneTempArrayList.isNotEmpty()) {
            adapterInRecyclerView(filterCloneTempArrayList.sortedBy { it.id.toInt() })
        }
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




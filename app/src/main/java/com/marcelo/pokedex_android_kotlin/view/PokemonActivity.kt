package com.marcelo.pokedex_android_kotlin.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.marcelo.pokedex_android_kotlin.R
import com.marcelo.pokedex_android_kotlin.model.PokemonModel
import java.util.*

var poke: Parcelable? = null

class PokemonActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pokemon)

        val toolbar = findViewById<Toolbar>(R.id.toolbar2)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""


        setupViews()

        val imagemView: ImageView = findViewById(R.id.imageView)
        val pokemonId: TextView = findViewById(R.id.txt_pokemonId)
        val pokemonName: TextView = findViewById(R.id.txt_pokemonName)
        val pokemonType01: TextView = findViewById(R.id.txt_pokemonType01)
        val pokemonType02: TextView = findViewById(R.id.txt_pokemonType02)
        val constraintLayout = findViewById<ConstraintLayout>(R.id.pokemonConstrain)


        val pokemon = intent.getParcelableExtra<PokemonModel>("pokemon")

        poke = pokemon

        pokemon?.let {
            Glide.with(this).load(pokemon.imageUrl).into(imagemView)
            pokemonId.text = "#${formattedNumber(pokemon.id)}"
            pokemonName.text = captalizerText(pokemon.name)
            pokemonType01.text = captalizerText(pokemon.types[0].name)

            changeColorForBackandLabel(pokemon.types[0].name, pokemonType01, constraintLayout)

            if (pokemon.types.size > 1) {
                pokemonType02.visibility = View.VISIBLE
                pokemonType02.text = captalizerText(pokemon.types[1].name)

                changeColorForBackandLabel(pokemon.types[1].name, pokemonType02)
            } else {
                pokemonType02.visibility = View.GONE
            }

        }


    }

    private fun setupViews() {
        val tabLayout: TabLayout = findViewById(R.id.add_tab)
        val viewPager: ViewPager2 = findViewById(R.id.add_viewpager)
        val adapter = TabViewPagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getString(adapter.tabs[position])
        }.attach()


    }

    fun captalizerText(text: String) = text.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.getDefault()
        ) else it.toString()
    }

    fun formattedNumber(id: String) = id.toString().padStart(3, '0')

    private fun changeColorForBackandLabel(
        type: String,
        txt: TextView,
        layout: ConstraintLayout? = null
    ) {
        when (type) {
            "grass" -> {
                txt.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_grass,
                    0,
                    0,
                    0
                )
                txt.setBackgroundColor(Color.parseColor("#56972f"))
                layout?.setBackgroundColor(Color.parseColor("#7AC74C"))
            }
            "bug" -> {
                txt.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_bug,
                    0,
                    0,
                    0
                )
                txt.setBackgroundColor(Color.parseColor("#6a7611"))
                layout?.setBackgroundColor(Color.parseColor("#A6B91A"))
            }
            "poison" -> {
                txt.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_poison,
                    0,
                    0,
                    0
                )
                txt.setBackgroundColor(Color.parseColor("#6c296a"))
                layout?.setBackgroundColor(Color.parseColor("#A33EA1"))
            }
            "normal" -> {
                txt.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_normal,
                    0,
                    0,
                    0
                )
                txt.setBackgroundColor(Color.parseColor("#818054"))
                layout?.setBackgroundColor(Color.parseColor("#A8A77A"))
            }

            "dark" -> {
                txt.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_dark,
                    0,
                    0,
                    0
                )
                txt.setBackgroundColor(Color.parseColor("#413229"))
                layout?.setBackgroundColor(Color.parseColor("#705746"))
            }
            "dragon" -> {
                txt.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_dragon,
                    0,
                    0,
                    0
                )
                txt.setBackgroundColor(Color.parseColor("#4403e1"))
                layout?.setBackgroundColor(Color.parseColor("#6F35FC"))
            }
            "electric" -> {
                txt.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_electric,
                    0,
                    0,
                    0
                )
                txt.setBackgroundColor(Color.parseColor("#f76b2c"))
                layout?.setBackgroundColor(Color.parseColor("#F7D02C"))
            }
            "fairy" -> {
                txt.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_fairy,
                    0,
                    0,
                    0
                )
                txt.setBackgroundColor(Color.parseColor("#c34c87"))
                layout?.setBackgroundColor(Color.parseColor("#D685AD"))
            }
            "fighting" -> {
                txt.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_fighting,
                    0,
                    0,
                    0
                )
                txt.setBackgroundColor(Color.parseColor("#831f1b"))
                layout?.setBackgroundColor(Color.parseColor("#C22E28"))
            }
            "fire" -> {
                txt.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_fire,
                    0,
                    0,
                    0
                )
                txt.setBackgroundColor(Color.parseColor("#c25c10"))
                layout?.setBackgroundColor(Color.parseColor("#EE8130"))
            }
            "ghost" -> {
                txt.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_ghost,
                    0,
                    0,
                    0
                )
                txt.setBackgroundColor(Color.parseColor("#4e3b66"))
                layout?.setBackgroundColor(Color.parseColor("#735797"))
            }
            "ground" -> {
                txt.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_ground,
                    0,
                    0,
                    0
                )
                txt.setBackgroundColor(Color.parseColor("#d3a328"))
                layout?.setBackgroundColor(Color.parseColor("#E2BF65"))
            }
            "ice" -> {
                txt.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_ice,
                    0,
                    0,
                    0
                )
                txt.setBackgroundColor(Color.parseColor("#5ec5c0"))
                layout?.setBackgroundColor(Color.parseColor("#96D9D6"))
            }
            "psychic" -> {
                txt.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_psychic,
                    0,
                    0,
                    0
                )
                txt.setBackgroundColor(Color.parseColor("#f60b53"))
                layout?.setBackgroundColor(Color.parseColor("#F95587"))
            }
            "rock" -> {
                txt.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_rock,
                    0,
                    0,
                    0
                )
                txt.setBackgroundColor(Color.parseColor("#7b6d24"))
                layout?.setBackgroundColor(Color.parseColor("#B6A136"))
            }
            "steel" -> {
                txt.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_steel,
                    0,
                    0,
                    0
                )
                txt.setBackgroundColor(Color.parseColor("#8989af"))
                layout?.setBackgroundColor(Color.parseColor("#B7B7CE"))
            }
            "water" -> {
                txt.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_water,
                    0,
                    0,
                    0
                )
                txt.setBackgroundColor(Color.parseColor("#1d5ee9"))
                layout?.setBackgroundColor(Color.parseColor("#6390F0"))
            }

        }
    }
}

class TabViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    val tabs = arrayOf(R.string.about, R.string.stats, R.string.evolution)
    val fragments = arrayOf(
        PokemonAboutFragment(),
        StatsFragment(),
        EvolutionFragment()
    )


    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putParcelable("message", poke)

        fragments[position].arguments = bundle

        return fragments[position]
    }


}


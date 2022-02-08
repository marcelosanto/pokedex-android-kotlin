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
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.marcelo.pokedex_android_kotlin.R
import com.marcelo.pokedex_android_kotlin.api.model.PokemonModel

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
        val txtType01 = findViewById<TextView>(R.id.txt_type01)
        val imgType01 = findViewById<ImageView>(R.id.img_type01)
        val type01Layout = findViewById<CardView>(R.id.type01_layout)
        val txtType02 = findViewById<TextView>(R.id.txt_type02)
        val imgType02 = findViewById<ImageView>(R.id.img_type02)
        val type02Layout = findViewById<CardView>(R.id.type02_layout)
        val constraintLayout = findViewById<ConstraintLayout>(R.id.pokemonConstrain)


        val pokemon = intent.getParcelableExtra<PokemonModel>("pokemon")

        poke = pokemon

        pokemon?.let {
            Glide.with(this).load(pokemon.imageUrl).into(imagemView)
            pokemonId.text = "#${formattedNumber(pokemon.id)}"
            pokemonName.text = captalizerText(pokemon.name)
            txtType01.text = captalizerText(pokemon.types[0].name)

            changeColorForBackandLabel(
                pokemon.types[0].name,
                imgType01,
                constraintLayout,
                type01Layout
            )

            if (pokemon.types.size > 1) {
                type02Layout.visibility = View.VISIBLE
                txtType02.text = captalizerText(pokemon.types[1].name)

                changeColorForBackandLabel(pokemon.types[1].name, imgType02, null, type02Layout)
            } else {
                type02Layout.visibility = View.INVISIBLE
            }

        }


    }

    private fun changeColorForBackandLabel(
        type: String,
        img: ImageView,
        layout: ConstraintLayout? = null,
        typeLayout: CardView? = null
    ) {
        when (type) {
            "flying" -> {
                img.setImageResource(R.drawable.ic_flying)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#a385e0"))
                layout?.setBackgroundColor(Color.parseColor("#614f86"))

            }
            "grass" -> {
                img.setImageResource(R.drawable.ic_grass)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#56972f"))
                layout?.setBackgroundColor(Color.parseColor("#7AC74C"))
            }
            "bug" -> {
                img.setImageResource(R.drawable.ic_bug)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#6a7611"))
                layout?.setBackgroundColor(Color.parseColor("#A6B91A"))
            }
            "poison" -> {
                img.setImageResource(R.drawable.ic_poison)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#6c296a"))
                layout?.setBackgroundColor(Color.parseColor("#A33EA1"))
            }
            "normal" -> {
                img.setImageResource(R.drawable.ic_normal)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#818054"))
                layout?.setBackgroundColor(Color.parseColor("#A8A77A"))
            }

            "dark" -> {
                img.setImageResource(R.drawable.ic_dark)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#413229"))
                layout?.setBackgroundColor(Color.parseColor("#705746"))
            }
            "dragon" -> {
                img.setImageResource(R.drawable.ic_dragon)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#4403e1"))
                layout?.setBackgroundColor(Color.parseColor("#6F35FC"))
            }
            "electric" -> {
                img.setImageResource(R.drawable.ic_electric)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#f76b2c"))
                layout?.setBackgroundColor(Color.parseColor("#F7D02C"))
            }
            "fairy" -> {
                img.setImageResource(R.drawable.ic_fairy)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#c34c87"))
                layout?.setBackgroundColor(Color.parseColor("#D685AD"))
            }
            "fighting" -> {
                img.setImageResource(R.drawable.ic_fighting)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#831f1b"))
                layout?.setBackgroundColor(Color.parseColor("#C22E28"))
            }
            "fire" -> {
                img.setImageResource(R.drawable.ic_fire)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#c25c10"))
                layout?.setBackgroundColor(Color.parseColor("#EE8130"))
            }
            "ghost" -> {
                img.setImageResource(R.drawable.ic_ghost)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#4e3b66"))
                layout?.setBackgroundColor(Color.parseColor("#735797"))
            }
            "ground" -> {
                img.setImageResource(R.drawable.ic_ground)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#d3a328"))
                layout?.setBackgroundColor(Color.parseColor("#E2BF65"))
            }
            "ice" -> {
                img.setImageResource(R.drawable.ic_ice)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#5ec5c0"))
                layout?.setBackgroundColor(Color.parseColor("#96D9D6"))
            }
            "psychic" -> {
                img.setImageResource(R.drawable.ic_psychic)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#f60b53"))
                layout?.setBackgroundColor(Color.parseColor("#F95587"))
            }
            "rock" -> {
                img.setImageResource(R.drawable.ic_rock)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#7b6d24"))
                layout?.setBackgroundColor(Color.parseColor("#B6A136"))
            }
            "steel" -> {
                img.setImageResource(R.drawable.ic_steel)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#8989af"))
                layout?.setBackgroundColor(Color.parseColor("#B7B7CE"))
            }
            "water" -> {
                img.setImageResource(R.drawable.ic_water)
                typeLayout?.setCardBackgroundColor(Color.parseColor("#1d5ee9"))
                layout?.setBackgroundColor(Color.parseColor("#6390F0"))
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
}

fun formattedNumber(id: String) = id.toString().padStart(3, '0')


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


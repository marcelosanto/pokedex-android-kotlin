package com.marcelo.pokedex_android_kotlin.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
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
import com.marcelo.pokedex_android_kotlin.domain.Pokemon
import com.marcelo.pokedex_android_kotlin.presentation.fragments.EvolutionFragment
import com.marcelo.pokedex_android_kotlin.presentation.fragments.PokemonAboutFragment
import com.marcelo.pokedex_android_kotlin.presentation.fragments.StatsFragment
import com.marcelo.pokedex_android_kotlin.presentation.fragments.captalizerText
import com.marcelo.pokedex_android_kotlin.utils.Const.changeColorForBackandLabelConstraint

class PokemonActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pokemon)

        val toolbar = findViewById<Toolbar>(R.id.toolbar2)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

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

        val pokemonInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra("POKEMON", Pokemon::class.java)!!
        else
            intent.getSerializableExtra("POKEMON") as Pokemon

        setupViews(pokemonInfo)

        pokemonInfo.let { pokemon ->
            Glide.with(this).load(pokemon.imageUrl).into(imagemView)
            pokemonId.text = "#${formattedNumber(pokemon.id)}"
            pokemonName.text = captalizerText(pokemon.name)
            txtType01.text = captalizerText(pokemon.types[0].name)

            changeColorForBackandLabelConstraint(
                pokemon.types[0].name,
                imgType01,
                constraintLayout,
                type01Layout
            )

            if (pokemon.types.size > 1) {
                type02Layout.visibility = View.VISIBLE
                txtType02.text = captalizerText(pokemon.types[1].name)

                changeColorForBackandLabelConstraint(
                    pokemon.types[1].name,
                    imgType02,
                    null,
                    type02Layout
                )
            } else {
                type02Layout.visibility = View.INVISIBLE
            }

        }


    }


    private fun setupViews(pokemonInfo: Pokemon) {
        val tabLayout: TabLayout = findViewById(R.id.add_tab)
        val viewPager: ViewPager2 = findViewById(R.id.add_viewpager)
        val adapter = TabViewPagerAdapter(this, pokemonInfo)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getString(adapter.tabs[position])
        }.attach()
    }
}

fun formattedNumber(id: String) = id.toString().padStart(3, '0')


class TabViewPagerAdapter(fa: FragmentActivity, pokemonInfo: Pokemon) : FragmentStateAdapter(fa) {
    val tabs = arrayOf(R.string.about, R.string.stats, R.string.evolution)
    val fragments = arrayOf(
        PokemonAboutFragment(),
        StatsFragment(),
        EvolutionFragment()
    )

    val pokemon = pokemonInfo

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putSerializable("message", pokemon)

        fragments[position].arguments = bundle

        return fragments[position]
    }


}


package com.marcelo.pokedex_android_kotlin.view

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
import com.marcelo.pokedex_android_kotlin.data.model.ModelPokemon
import com.marcelo.pokedex_android_kotlin.databinding.ActivityPokemonBinding
import com.marcelo.pokedex_android_kotlin.presentation.fragments.EvolutionFragment
import com.marcelo.pokedex_android_kotlin.presentation.fragments.PokemonAboutFragment
import com.marcelo.pokedex_android_kotlin.presentation.fragments.StatsFragment
import com.marcelo.pokedex_android_kotlin.presentation.fragments.captalizerText
import com.marcelo.pokedex_android_kotlin.utils.Const.changeColorForBackandLabelConstraint
import com.marcelo.pokedex_android_kotlin.utils.Const.formattedNumber

class PokemonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val pokemonInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            intent.getSerializableExtra("POKEMON", ModelPokemon::class.java)!!
        else
            intent.getSerializableExtra("POKEMON") as ModelPokemon

        setupViews(pokemonInfo)

        pokemonInfo.let { pokemon ->
            binding.run {
                Glide.with(this@PokemonActivity).load(pokemon.imageUrl).into(imageView)
                txtPokemonId.text = "#${formattedNumber(pokemon.id)}"
                txtPokemonName.text = captalizerText(pokemon.name)
                txtType01.text = captalizerText(pokemon.types[0].name)

                changeColorForBackandLabelConstraint(
                    pokemon.types[0].name,
                    imgType01,
                    pokemonConstrain,
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


    }

    private fun setupViews(pokemonInfo: ModelPokemon) {
        val adapter = TabViewPagerAdapter(this, pokemonInfo)

        binding.run {
            addViewpager.adapter = adapter

            TabLayoutMediator(addTab, addViewpager) { tab, position ->
                tab.text = getString(adapter.tabs[position])
            }.attach()
        }

    }
}

class TabViewPagerAdapter(fa: FragmentActivity, pokemonInfo: ModelPokemon) :
    FragmentStateAdapter(fa) {
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


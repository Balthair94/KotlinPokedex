package baltamon.mx.kotlinpokedex.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import baltamon.mx.kotlinpokedex.fragments.PokemonAbilitiesFragment
import baltamon.mx.kotlinpokedex.fragments.PokemonAboutFragment
import baltamon.mx.kotlinpokedex.fragments.PokemonMovesFragment
import baltamon.mx.kotlinpokedex.models.NamedAPIResource
import baltamon.mx.kotlinpokedex.models.Pokemon

/**
 * Created by Baltazar Rodriguez on 28/05/2017.
 */
class TabPokemonFragmentAdapter(fm: FragmentManager, val pokemon: Pokemon) : FragmentPagerAdapter(fm) {
    val titles = arrayOf("About", "Abilities", "Moves")

    override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> PokemonAboutFragment.newInstance(pokemon)
                1 -> {
                    val abilities: ArrayList<NamedAPIResource> = ArrayList()
                    pokemon.abilities.mapTo(abilities) { it.ability }
                    PokemonAbilitiesFragment().newInstance(abilities)
                }
                else -> {
                    val moves: ArrayList<NamedAPIResource> = ArrayList()
                    pokemon.moves.mapTo(moves) { it.move }
                    PokemonMovesFragment.newInstance(moves)
                }
            }

    override fun getPageTitle(position: Int): CharSequence = titles[position]

    override fun getCount(): Int = 3
}
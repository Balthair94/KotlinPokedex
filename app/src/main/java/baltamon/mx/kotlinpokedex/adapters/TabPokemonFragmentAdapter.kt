package baltamon.mx.kotlinpokedex.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import baltamon.mx.kotlinpokedex.fragments.PokemonAbilitiesFragment
import baltamon.mx.kotlinpokedex.fragments.PokemonAboutFragment
import baltamon.mx.kotlinpokedex.fragments.PokemonMovesFragment

/**
 * Created by Baltazar Rodriguez on 28/05/2017.
 */
class TabPokemonFragmentAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position){
            0 -> return PokemonAboutFragment()
            1 -> return PokemonAbilitiesFragment()
            else -> return PokemonMovesFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        val titles = arrayOf("About", "Abilities", "Moves")
        return titles[position]
    }


}
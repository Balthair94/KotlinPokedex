package baltamon.mx.kotlinpokedex.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import baltamon.mx.kotlinpokedex.R
import baltamon.mx.kotlinpokedex.adapters.RVAdapterPokemonAbilities
import baltamon.mx.kotlinpokedex.models.NamedAPIResource
import kotlinx.android.synthetic.main.fragment_pokemon_abilities.*

/**
 * Created by Baltazar Rodriguez on 28/05/2017.
 */

private const val MY_OBJECT_KEY = "abilities_list"

class PokemonAbilitiesFragment : Fragment() {
    companion object {
        fun newInstance(abilities: ArrayList<NamedAPIResource>): PokemonAbilitiesFragment {
            val fragment = PokemonAbilitiesFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList(MY_OBJECT_KEY, abilities)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pokemon_abilities, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showAbilities()
    }

    fun showAbilities() {
        val abilities = arguments.getIntegerArrayList(MY_OBJECT_KEY) as ArrayList<NamedAPIResource>

        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(context)
        recycler_view.adapter = RVAdapterPokemonAbilities(abilities, fragmentManager)
    }
}
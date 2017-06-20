package baltamon.mx.kotlinpokedex.fragments

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import baltamon.mx.kotlinpokedex.R
import baltamon.mx.kotlinpokedex.adapters.RVAdapterPokemonAbilities
import baltamon.mx.kotlinpokedex.models.NamedAPIResource
import baltamon.mx.kotlinpokedex.models.Pokemon
import baltamon.mx.kotlinpokedex.models.PokemonAbility
import kotlinx.android.synthetic.main.fragment_pokemon_abilities.view.*

/**
 * Created by Baltazar Rodriguez on 28/05/2017.
 */

private const val MY_OBJECT_KEY = "abilities_list"

class PokemonAbilitiesFragment : Fragment() {

    fun newInstance(abilities: ArrayList<NamedAPIResource>): PokemonAbilitiesFragment{
        val fragment = PokemonAbilitiesFragment()
        val bundle = Bundle()
        bundle.putParcelableArrayList(MY_OBJECT_KEY, abilities)
        fragment.arguments = bundle
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_abilities, container, false)
        showAbilities(view)
        return view
    }

    fun showAbilities(view: View){
        val recyclerView = view.recycler_view
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        loadAbilities(recyclerView)
    }

    fun loadAbilities(recyclerView: RecyclerView){
        val abilities = arguments.getIntegerArrayList(MY_OBJECT_KEY) as ArrayList<NamedAPIResource>
        val adapter = RVAdapterPokemonAbilities(abilities, fragmentManager)
        recyclerView.adapter = adapter
    }

}
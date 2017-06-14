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
import baltamon.mx.kotlinpokedex.models.Pokemon
import kotlinx.android.synthetic.main.fragment_pokemon_abilities.view.*

/**
 * Created by Baltazar Rodriguez on 28/05/2017.
 */

private const val MY_OBJECT_KEY = "pokemon_object"

class PokemonAbilitiesFragment : Fragment() {

    fun newInstance(pokemon: Pokemon): PokemonAbilitiesFragment{
        val fragment = PokemonAbilitiesFragment()
        val bundle = Bundle()
        bundle.putParcelable(MY_OBJECT_KEY, pokemon)
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
        var recyclerView = view.recycler_view
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        loadAbilities(recyclerView)
    }

    fun loadAbilities(recyclerView: RecyclerView){
        val pokemon = arguments.getParcelable<Parcelable>(MY_OBJECT_KEY) as Pokemon
        var adapter = RVAdapterPokemonAbilities(pokemon.abilities, context)
        recyclerView.adapter = adapter
    }

}
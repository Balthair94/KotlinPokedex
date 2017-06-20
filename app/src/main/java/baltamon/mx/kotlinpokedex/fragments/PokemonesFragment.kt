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
import baltamon.mx.kotlinpokedex.adapters.RVAdapterPokemon
import baltamon.mx.kotlinpokedex.models.NamedAPIResource

/**
 * Created by Baltazar Rodriguez on 31/05/2017.
 */

private const val MY_OBJECT_KEY = "pokemones_list"

class PokemonesFragment : Fragment() {
    companion object {
        fun newInstance(pokemones: ArrayList<NamedAPIResource>): PokemonesFragment {

            val fragment = PokemonesFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList(MY_OBJECT_KEY, pokemones)
            fragment.arguments = bundle
            return fragment
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pokemones, container, false)
        loadView(view)
        return view
    }

    fun loadView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val pokemonList = arguments.getParcelableArrayList<Parcelable>(MY_OBJECT_KEY) as ArrayList<NamedAPIResource>
        val adapter = RVAdapterPokemon(pokemonList, context)
        recyclerView.adapter = adapter
    }

}
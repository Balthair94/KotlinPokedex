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
import baltamon.mx.kotlinpokedex.adapters.RVAdapterPokemonMoves
import baltamon.mx.kotlinpokedex.models.Pokemon
import kotlinx.android.synthetic.main.fragment_pokemon_moves.view.*

/**
 * Created by Baltazar Rodriguez on 28/05/2017.
 */

private const val MY_OBJECT_KEY = "pokemon_object"

class PokemonMovesFragment : Fragment() {

    fun newInstance(pokemon: Pokemon): PokemonMovesFragment{
        val fragment = PokemonMovesFragment()
        val bundle = Bundle()
        bundle.putParcelable(MY_OBJECT_KEY, pokemon)
        fragment.arguments = bundle
        return fragment
    }

    fun showMoves(view: View){
        var recyclerView = view.recycler_view
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        loadMoves(recyclerView)
    }

    fun loadMoves(recyclerView: RecyclerView){
        val pokemon = arguments.getParcelable<Parcelable>(MY_OBJECT_KEY) as Pokemon
        var adapter = RVAdapterPokemonMoves(pokemon.moves, context, fragmentManager)
        recyclerView.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_moves, container, false)
        showMoves(view)
        return view
    }

}
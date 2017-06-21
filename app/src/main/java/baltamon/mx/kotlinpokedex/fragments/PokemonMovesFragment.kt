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
import baltamon.mx.kotlinpokedex.models.NamedAPIResource
import kotlinx.android.synthetic.main.fragment_pokemon_moves.view.*

/**
 * Created by Baltazar Rodriguez on 28/05/2017.
 */

private const val MY_OBJECT_KEY = "moves_list"

class PokemonMovesFragment : Fragment() {

    companion object {
        fun newInstance(moves: ArrayList<NamedAPIResource>): PokemonMovesFragment {
            val fragment = PokemonMovesFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList(MY_OBJECT_KEY, moves)
            fragment.arguments = bundle
            return fragment
        }
    }

    fun showMoves(view: View) {
        val recyclerView = view.recycler_view
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        loadMoves(recyclerView)
    }

    fun loadMoves(recyclerView: RecyclerView) {
        val moves = arguments.getParcelableArrayList<Parcelable>(MY_OBJECT_KEY) as ArrayList<NamedAPIResource>
        var adapter = RVAdapterPokemonMoves(moves, context, fragmentManager)
        recyclerView.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_moves, container, false)
        showMoves(view)
        return view
    }

}
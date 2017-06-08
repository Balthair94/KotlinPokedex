package baltamon.mx.kotlinpokedex.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import baltamon.mx.kotlinpokedex.R

/**
 * Created by Baltazar Rodriguez on 01/06/2017.
 */
class PokemonTypesFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_types, container, false)

        return view
    }

}
package baltamon.mx.kotlinpokedex.fragments

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import baltamon.mx.kotlinpokedex.R
import baltamon.mx.kotlinpokedex.models.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pokemon_about.view.*

/**
 * Created by Baltazar Rodriguez on 28/05/2017.
 */

private const val MY_OBJECT_KEY = "pokemon"

class PokemonAboutFragment : Fragment() {

    companion object {
        fun newInstance(pokemon: Pokemon): PokemonAboutFragment {
            val fragment = PokemonAboutFragment()
            val bundle = Bundle()
            bundle.putParcelable(MY_OBJECT_KEY, pokemon)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_about, container, false)
        showDetails(view)
        return view
    }

    fun showDetails(view: View) {
        val pokemon: Pokemon = arguments.getParcelable<Parcelable>(MY_OBJECT_KEY) as Pokemon
        view.tv_pokemon_name.text = pokemon.name
        view.tv_weight.text = "Weight: " + pokemon.weight.toString()
        view.tv_hight.text = "Hight: " + pokemon.height.toString()
        Picasso.with(context).load(pokemon.sprites.front_default).into(view.iv_pokemon_image)
        Picasso.with(context).load(pokemon.sprites.front_default).into(view.iv_form1)
        Picasso.with(context).load(pokemon.sprites.back_default).into(view.iv_form2)

        var textTypes = "Types: "
        for (item in pokemon.types) {
            textTypes += item.type.name + ", "
        }
        view.tv_types.text = textTypes
    }

}
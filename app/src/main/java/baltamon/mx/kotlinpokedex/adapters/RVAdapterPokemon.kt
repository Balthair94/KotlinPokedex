package baltamon.mx.kotlinpokedex.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import baltamon.mx.kotlinpokedex.R
import baltamon.mx.kotlinpokedex.activities.PokemonDetailActivity
import baltamon.mx.kotlinpokedex.extension.inflate
import baltamon.mx.kotlinpokedex.models.NamedAPIResource
import kotlinx.android.synthetic.main.item_title.view.*

/**
 * Created by Baltazar Rodriguez on 11/06/2017.
 */
private const val INTENT_POKEMON_NAME = "pokemon_name"

class RVAdapterPokemon(val pokemonList: ArrayList<NamedAPIResource>,
                       val context: Context) : RecyclerView.Adapter<RVAdapterPokemon.PokemonViewHolder>() {

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cv: CardView = itemView.card_view
        val textView: TextView = itemView.tv_title_item
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
            PokemonViewHolder(parent.inflate(R.layout.item_title))

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.textView.text = pokemonList[position].name
        holder.cv.setOnClickListener {
            val intent = Intent(context, PokemonDetailActivity::class.java)
            intent.putExtra(INTENT_POKEMON_NAME, pokemonList[position].name)
            context.startActivity(intent)
        }
    }
}
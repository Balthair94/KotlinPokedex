package baltamon.mx.kotlinpokedex.adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import baltamon.mx.kotlinpokedex.R
import baltamon.mx.kotlinpokedex.activities.PokemonDetailActivity
import baltamon.mx.kotlinpokedex.models.NamedAPIResource

/**
 * Created by Baltazar Rodriguez on 11/06/2017.
 */

private const val INTENT_POKEMON_URL = "pokemon_url"

class RVAdapterPokemones internal constructor(pokemones: List<NamedAPIResource>, context: Context) :
        RecyclerView.Adapter<RVAdapterPokemones.PokemonViewHolder>() {

    internal var pokemones: List<NamedAPIResource> = pokemones
    internal var context: Context = context

    class PokemonViewHolder internal constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var cv: CardView = itemView.findViewById(R.id.card_view) as CardView
        internal var textView: TextView = itemView.findViewById(R.id.tv_pokemon_name) as TextView
    }

    override fun getItemCount(): Int {
        return pokemones.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        val pvh = PokemonViewHolder(v)
        return pvh
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.textView.text = pokemones[position].name
        holder.cv.setOnClickListener {
            val intent = Intent(context, PokemonDetailActivity::class.java)
            intent.putExtra(INTENT_POKEMON_URL, pokemones[position].url)
            context.startActivity(intent)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
    }

}
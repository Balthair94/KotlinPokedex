package baltamon.mx.kotlinpokedex.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import baltamon.mx.kotlinpokedex.R
import baltamon.mx.kotlinpokedex.extension.inflate
import baltamon.mx.kotlinpokedex.models.NamedAPIResource
import kotlinx.android.synthetic.main.item_title.view.*

/**
 * Created by Baltazar Rodriguez on 18/06/2017.
 */
class RVAdapterPokemonTypes(val types: ArrayList<NamedAPIResource>) :
        RecyclerView.Adapter<RVAdapterPokemonTypes.TypeViewHolder>() {

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        holder.typeName.text = types[position].name
    }

    override fun getItemCount(): Int = types.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder =
            RVAdapterPokemonTypes.TypeViewHolder(parent.inflate(R.layout.item_title))

    class TypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val typeName: TextView = itemView.tv_title_item
    }
}
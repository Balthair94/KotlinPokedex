package baltamon.mx.kotlinpokedex.adapters

import android.app.FragmentManager
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import baltamon.mx.kotlinpokedex.R
import baltamon.mx.kotlinpokedex.models.NamedAPIResource
import kotlinx.android.synthetic.main.item_title.view.*

/**
 * Created by Baltazar Rodriguez on 18/06/2017.
 */
class RVAdapterPokemonTypes internal constructor(internal var types: ArrayList<NamedAPIResource>,
                                                 internal var context: Context):
        RecyclerView.Adapter<RVAdapterPokemonTypes.TypeViewHolder>(){

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        holder.typeName.text = types[position].name
    }

    override fun getItemCount(): Int {
        return types.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TypeViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_title, parent, false)
        val pvh = RVAdapterPokemonTypes.TypeViewHolder(v)
        return pvh
    }

    class TypeViewHolder internal constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var typeName = itemView.tv_title_item
    }

}
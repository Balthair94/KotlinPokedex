package baltamon.mx.kotlinpokedex.adapters

import android.support.v4.app.FragmentManager
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import baltamon.mx.kotlinpokedex.R
import baltamon.mx.kotlinpokedex.extension.inflate
import baltamon.mx.kotlinpokedex.fragments.AbilityDialogFragment
import baltamon.mx.kotlinpokedex.models.NamedAPIResource
import kotlinx.android.synthetic.main.item_title.view.*

/**
 * Created by Baltazar Rodriguez on 13/06/2017.
 */
class RVAdapterPokemonAbilities(val abilities: ArrayList<NamedAPIResource>,
                                val fragmentManager: FragmentManager) :
        RecyclerView.Adapter<RVAdapterPokemonAbilities.AbilityViewHolder>() {

    class AbilityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAbilityName: TextView = itemView.tv_title_item
        val cardView: CardView = itemView.card_view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder =
            RVAdapterPokemonAbilities.AbilityViewHolder(parent.inflate(R.layout.item_title))

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        holder.tvAbilityName.text = abilities[position].name
        holder.cardView.setOnClickListener {
            val dialogFragment = AbilityDialogFragment().newInstance(abilities[position])
            dialogFragment.show(fragmentManager, "Detail")
        }
    }

    override fun getItemCount(): Int = abilities.size
}
package baltamon.mx.kotlinpokedex.adapters

import android.support.v4.app.FragmentManager
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import baltamon.mx.kotlinpokedex.R
import baltamon.mx.kotlinpokedex.fragments.AbilityDialogFragment
import baltamon.mx.kotlinpokedex.models.PokemonAbility
import kotlinx.android.synthetic.main.item_title.view.*

/**
 * Created by Baltazar Rodriguez on 13/06/2017.
 */
class RVAdapterPokemonAbilities internal constructor(internal var abilities: List<PokemonAbility>,
                                                     internal var context: Context,
                                                     internal var fragmentManager: FragmentManager) :
        RecyclerView.Adapter<RVAdapterPokemonAbilities.AbilityViewHolder>() {

    class AbilityViewHolder internal constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var abilityName = itemView.tv_title_item
        internal var cardView = itemView.card_view
    }

    override fun getItemCount(): Int {
        return abilities.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
        val pvh = RVAdapterPokemonAbilities.AbilityViewHolder(v)
        return pvh
    }

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        holder.abilityName.text = abilities[position].ability.name
        holder.cardView.setOnClickListener {
            val dialogFragment = AbilityDialogFragment().
                    newInstance(abilities[position].ability)
            dialogFragment.show(fragmentManager, "Detail")
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
    }
}
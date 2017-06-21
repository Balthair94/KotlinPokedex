package baltamon.mx.kotlinpokedex.adapters

import android.support.v4.app.FragmentManager
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import baltamon.mx.kotlinpokedex.R
import baltamon.mx.kotlinpokedex.extension.inflate
import baltamon.mx.kotlinpokedex.fragments.MoveDialogFragment
import baltamon.mx.kotlinpokedex.models.NamedAPIResource
import kotlinx.android.synthetic.main.item_title.view.*

/**
 * Created by Baltazar Rodriguez on 14/06/2017.
 */
class RVAdapterPokemonMoves(val moves: ArrayList<NamedAPIResource>,
                            val fragmentManager: FragmentManager) :
        RecyclerView.Adapter<RVAdapterPokemonMoves.MoveViewHolder>() {

    class MoveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMoveName: TextView = itemView.tv_title_item
        val cardView: CardView = itemView.card_view
    }

    override fun onBindViewHolder(holder: MoveViewHolder, position: Int) {
        holder.tvMoveName.text = moves[position].name
        holder.cardView.setOnClickListener {
            val dialogFragment = MoveDialogFragment.newInstance(moves[position])
            dialogFragment.show(fragmentManager, "Detail")
        }
    }

    override fun getItemCount(): Int = moves.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveViewHolder =
            RVAdapterPokemonMoves.MoveViewHolder(parent.inflate(R.layout.item_title))
}
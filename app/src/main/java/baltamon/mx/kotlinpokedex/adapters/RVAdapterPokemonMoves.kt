package baltamon.mx.kotlinpokedex.adapters

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import baltamon.mx.kotlinpokedex.R
import baltamon.mx.kotlinpokedex.fragments.MoveDialogFragment
import baltamon.mx.kotlinpokedex.models.PokemonMove
import kotlinx.android.synthetic.main.item_title.view.*

/**
 * Created by Baltazar Rodriguez on 14/06/2017.
 */
class RVAdapterPokemonMoves internal constructor(moves: List<PokemonMove>, context: Context,
                                                 internal var fragmentManager: FragmentManager) :
        RecyclerView.Adapter<RVAdapterPokemonMoves.MoveViewHolder>() {

    internal var moves = moves
    internal var context = context

    class MoveViewHolder internal constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        internal var moveName = itemView.tv_title_item
        internal var cardView = itemView.card_view
    }

    override fun onBindViewHolder(holder: MoveViewHolder, position: Int) {
        holder.moveName.text = moves[position].move.name
        holder.cardView.setOnClickListener {
            val dialogFragment = MoveDialogFragment().
                    newInstance(moves[position].move)
            dialogFragment.show(fragmentManager, "Detail")
        }
    }

    override fun getItemCount(): Int {
        return moves.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_title, parent, false)
        val pvh = RVAdapterPokemonMoves.MoveViewHolder(v)
        return pvh
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        super.onAttachedToRecyclerView(recyclerView)
    }
}
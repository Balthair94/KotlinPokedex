package baltamon.mx.kotlinpokedex.fragments

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import baltamon.mx.kotlinpokedex.R
import baltamon.mx.kotlinpokedex.data.Move
import baltamon.mx.kotlinpokedex.interfaces.PokeAPIClient
import baltamon.mx.kotlinpokedex.interfaces.buildGson
import baltamon.mx.kotlinpokedex.interfaces.buildRetrofit
import baltamon.mx.kotlinpokedex.models.NamedAPIResource
import baltamon.mx.kotlinpokedex.showToast
import kotlinx.android.synthetic.main.dialog_fragment_move.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Baltazar Rodriguez on 17/06/2017.
 */

private const val MY_OBJECT_KEY = "pokemon_move"

class MoveDialogFragment : DialogFragment() {
    companion object {
        fun newInstance(move: NamedAPIResource): MoveDialogFragment {
            val dialog = MoveDialogFragment()
            val bundle = Bundle()
            bundle.putParcelable(MY_OBJECT_KEY, move)
            dialog.arguments = bundle
            return dialog
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_fragment_move, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showMoveInformation()
    }

    fun showMoveInformation() {
        val move = arguments.getParcelable<Parcelable>(MY_OBJECT_KEY) as NamedAPIResource
        tv_move_name.text = move.name
        loadMove(move.name)
    }

    fun loadMove(name: String) {
        val restClient = buildRetrofit(buildGson()).create(PokeAPIClient::class.java)
        val call = restClient.getMove(name)

        call.enqueue(object : Callback<Move> {
            override fun onResponse(call: Call<Move>?, response: Response<Move>) {
                when (response.code()) {
                    200 -> {
                        response.body()?.let {
                            tv_loading_message.visibility = View.GONE
                            layout_main.visibility = View.VISIBLE
                            tv_move_name.text = it.name
                            tv_move_pp.text = "PP: ${it.pp}"
                            tv_move_power.text = "Power: ${it.power}"
                            tv_move_damage_class.text = "Damage Class: ${it.damage_class.name}"
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Move>?, t: Throwable?) {
                context.showToast(t.toString())
                dismiss()
            }
        })

    }
}
package baltamon.mx.kotlinpokedex.fragments

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import baltamon.mx.kotlinpokedex.R
import baltamon.mx.kotlinpokedex.data.Move
import baltamon.mx.kotlinpokedex.interfaces.PokeAPIClient
import baltamon.mx.kotlinpokedex.models.NamedAPIResource
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.dialog_fragment_move.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Baltazar Rodriguez on 17/06/2017.
 */

private const val MY_OBJECT_KEY = "pokemon_move"

class MoveDialogFragment : DialogFragment() {
    fun newInstance(move: NamedAPIResource): MoveDialogFragment {
        val dialog = MoveDialogFragment()
        val bundle = Bundle()
        bundle.putParcelable(MY_OBJECT_KEY, move)
        dialog.arguments = bundle
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_fragment_move, container, false)
        showMoveInformation(view)
        return view
    }

    fun showMoveInformation(view: View) {
        val move = arguments.getParcelable<Parcelable>(MY_OBJECT_KEY) as NamedAPIResource
        view.tv_move_name.text = move.name
        loadMove(view, move.name)
    }

    fun loadMove(view: View, name: String) {
        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        val retrofit = Retrofit.Builder().baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        val restClient = retrofit.create(PokeAPIClient::class.java)
        val call = restClient.getMove(name)

        call.enqueue(object : Callback<Move> {

            override fun onResponse(call: Call<Move>?, response: Response<Move>) {
                when (response.code()) {
                    200 -> {
                        val move = response.body()!!
                        view.tv_loading_message.visibility = View.GONE
                        view.layout_main.visibility = View.VISIBLE
                        view.tv_move_name.text = move.name
                        view.tv_move_pp.text = "PP: " + move.pp
                        view.tv_move_power.text = "Power: " + move.power
                        view.tv_move_damage_class.text = "Damage Class: " + move.damage_class.name
                    }
                }
            }

            override fun onFailure(call: Call<Move>?, t: Throwable?) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show()
                dismiss()
            }
        })

    }
}
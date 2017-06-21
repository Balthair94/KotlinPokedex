package baltamon.mx.kotlinpokedex.fragments

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import baltamon.mx.kotlinpokedex.R
import baltamon.mx.kotlinpokedex.data.Ability
import baltamon.mx.kotlinpokedex.interfaces.PokeAPIClient
import baltamon.mx.kotlinpokedex.models.NamedAPIResource
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.dialog_fragment_ability.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Baltazar Rodriguez on 15/06/2017.
 */

private const val MY_OBJECT_KEY = "pokemon_ability"

class AbilityDialogFragment : DialogFragment() {

    fun newInstance(ability: NamedAPIResource): AbilityDialogFragment {
        val dialog = AbilityDialogFragment()
        val bundle = Bundle()
        bundle.putParcelable(MY_OBJECT_KEY, ability)
        dialog.arguments = bundle
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_fragment_ability, container, false)

        dialog.setTitle("Detail")

        showAbilityInformation(view)

        return view
    }

    fun showAbilityInformation(view: View) {
        val ability = arguments.getParcelable<Parcelable>(MY_OBJECT_KEY) as NamedAPIResource
        view.tv_ability_name.text = ability.name
        view.tv_ability_description.text = "Loading..."
        loadAbility(ability.name, view)

    }

    fun loadAbility(name: String, view: View) {
        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        val retrofit = Retrofit.Builder().baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        val restClient = retrofit.create(PokeAPIClient::class.java)
        val call = restClient.getAbility(name)

        call.enqueue(object : Callback<Ability> {
            override fun onResponse(call: Call<Ability>?, response: Response<Ability>) {

                when (response.code()) {
                    200 -> {
                        val ability = response.body()!!
                        view.tv_ability_description.text = ability.effect_entries[0].effect
                    }
                    else -> Log.i("ERROR", "DATA ERROR")
                }

            }

            override fun onFailure(call: Call<Ability>?, t: Throwable?) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show()
                dismiss()
            }

        })
    }

}
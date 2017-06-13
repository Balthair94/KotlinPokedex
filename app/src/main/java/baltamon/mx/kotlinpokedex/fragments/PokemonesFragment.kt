package baltamon.mx.kotlinpokedex.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import baltamon.mx.kotlinpokedex.R
import baltamon.mx.kotlinpokedex.adapters.RVAdapterPokemones
import baltamon.mx.kotlinpokedex.interfaces.RestClient
import baltamon.mx.kotlinpokedex.models.NamedAPIResourceList
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Baltazar Rodriguez on 31/05/2017.
 */
class PokemonesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pokemones, container, false)

        loadView(view)

        return view
    }

    fun loadView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        loadJson(recyclerView)
    }

    fun loadJson(recyclerView: RecyclerView){
        val dialog = ProgressDialog.show(context, "Loading", "Loading, please wait...", true)

        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        val retrofit = Retrofit.Builder().baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        val restClient = retrofit.create(RestClient::class.java)
        val call = restClient.pokemones

        call.enqueue(object: Callback<NamedAPIResourceList>{
            override fun onResponse(call: Call<NamedAPIResourceList>, response: Response<NamedAPIResourceList>) {
                when (response.code()){
                    200 -> {
                        val pokemonList: NamedAPIResourceList? = response.body()
                        val adapter = RVAdapterPokemones(pokemonList!!.results)
                        recyclerView.adapter = adapter
                    }
                    else -> Log.i("ERROR", "DATA ERROR")
                }
                dialog.dismiss()
            }

            override fun onFailure(call: Call<NamedAPIResourceList>?, t: Throwable?) {
                Log.i("ERROR", "NO DATA")
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

        })
    }

}
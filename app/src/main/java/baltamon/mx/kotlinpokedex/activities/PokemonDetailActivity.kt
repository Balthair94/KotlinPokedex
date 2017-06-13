package baltamon.mx.kotlinpokedex.activities

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import baltamon.mx.kotlinpokedex.R
import baltamon.mx.kotlinpokedex.adapters.TabPokemonFragmentAdapter
import baltamon.mx.kotlinpokedex.interfaces.RestClient
import baltamon.mx.kotlinpokedex.models.Pokemon
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Baltazar Rodriguez on 28/05/2017.
 */

private const val INTENT_POKEMON_NAME = "pokemon_name"

class PokemonDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        setUpToolbar()
        loadPokemonInformation()
    }

    fun loadPokemonInformation(){
        val dialog = ProgressDialog.show(this, "Loading", "Loading, please wait...", true)

        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        val retrofit = Retrofit.Builder().baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        val restClient = retrofit.create(RestClient::class.java)
        val call = restClient.getPokemon(intent.getStringExtra(INTENT_POKEMON_NAME))

        call.enqueue(object: Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>?, response: Response<Pokemon>) {
                when (response.code()){
                    200 -> {
                        val pokemon: Pokemon = response.body()!!
                        setUpTabView(pokemon)
                    }
                }
                dialog.dismiss()
            }

            override fun onFailure(call: Call<Pokemon>?, t: Throwable?) {
                dialog.dismiss()
                showToast(t.toString())
            }
        })
    }

    fun showToast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

    fun setUpToolbar() {
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Pokemon Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    fun setUpTabView(pokemon: Pokemon){
        view_pager.adapter = TabPokemonFragmentAdapter(supportFragmentManager, pokemon)
        tab_layout.setupWithViewPager(view_pager)
    }
}
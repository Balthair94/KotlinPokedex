package baltamon.mx.kotlinpokedex.activities

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import baltamon.mx.kotlinpokedex.R
import baltamon.mx.kotlinpokedex.adapters.TabPokemonFragmentAdapter
import baltamon.mx.kotlinpokedex.interfaces.PokeAPIClient
import baltamon.mx.kotlinpokedex.interfaces.buildGson
import baltamon.mx.kotlinpokedex.interfaces.buildRetrofit
import baltamon.mx.kotlinpokedex.models.Pokemon
import baltamon.mx.kotlinpokedex.showToast
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    fun loadPokemonInformation() {

        val dialog = ProgressDialog.show(this, getString(R.string.loading), "Loading, please wait...", true)
        val retrofit = buildRetrofit(buildGson())

        val restClient = retrofit.create(PokeAPIClient::class.java)
        val call = restClient.getPokemon(intent.getStringExtra(INTENT_POKEMON_NAME))

        call.enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>?, response: Response<Pokemon>) {
                when (response.code()) {
                    200 -> response.body()?.let { setUpTabView(it) }
                }
                dialog.dismiss()
            }

            override fun onFailure(call: Call<Pokemon>?, t: Throwable?) {
                dialog.dismiss()
                showToast(t.toString())
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
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

    fun setUpTabView(pokemon: Pokemon) {
        view_pager.adapter = TabPokemonFragmentAdapter(supportFragmentManager, pokemon)
        tab_layout.setupWithViewPager(view_pager)
    }
}
package baltamon.mx.kotlinpokedex

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import baltamon.mx.kotlinpokedex.fragments.PokemonMovesFragment
import baltamon.mx.kotlinpokedex.fragments.PokemonesFragment
import baltamon.mx.kotlinpokedex.interfaces.RestClient
import baltamon.mx.kotlinpokedex.models.Generation
import baltamon.mx.kotlinpokedex.models.NamedAPIResource
import baltamon.mx.kotlinpokedex.models.PokemonMove
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var pokemonesList: ArrayList<NamedAPIResource> = ArrayList()
    var movesList: ArrayList<NamedAPIResource> = ArrayList()
    var typesList: ArrayList<NamedAPIResource> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()
        setUpNavigationView()
        loadPokemonGeneration()
    }

    fun loadPokemonGeneration(){
        val dialog = ProgressDialog.show(this, "Loading", "Loading, please wait...", true)

        val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        val retrofit = Retrofit.Builder().baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        val restClient = retrofit.create(RestClient::class.java)
        val call = restClient.generation

        call.enqueue(object: Callback<Generation>{
            override fun onResponse(call: Call<Generation>?, response: Response<Generation>) {
                when(response.code()){
                    200 -> {
                        val generation = response.body()!!
                        pokemonesList = generation.pokemon_species
                        movesList = generation.moves
                        typesList = generation.types
                        loadFragment(1)
                    }
                }
                dialog.dismiss()
            }

            override fun onFailure(call: Call<Generation>?, t: Throwable?) {
                showToast(t.toString())
                dialog.dismiss()
            }

        })
    }

    fun loadFragment(fragment: Int){
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        when(fragment){
            1 -> fragmentTransaction.replace(R.id.frame_layout, PokemonesFragment().newInstance(pokemonesList))
            2 -> fragmentTransaction.replace(R.id.frame_layout, PokemonMovesFragment())
            else -> showToast("No Fragment Selected")
        }

        fragmentTransaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> openDrawer()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (isDrawerOpen()) closeDrawer()
        else super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_pokemons -> loadFragment(1)
            R.id.item_moves -> loadFragment(2)
            R.id.item_abilities -> showToast("Abilities")
            R.id.item_types -> showToast("Types")
            else -> showToast("No option selected")
        }

        return true
    }

    fun setUpNavigationView() {
        navigation_view.setNavigationItemSelectedListener(this)
    }


    fun setToolbar() {
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Pokedex"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    fun openDrawer() {
        drawer_layout.openDrawer(GravityCompat.START)
    }

    fun closeDrawer() {
        drawer_layout.closeDrawers()
    }

    fun isDrawerOpen(): Boolean {
        return drawer_layout.isDrawerOpen(GravityCompat.START)
    }

    fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}

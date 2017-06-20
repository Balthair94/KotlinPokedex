package baltamon.mx.kotlinpokedex

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import baltamon.mx.kotlinpokedex.fragments.PokemonMovesFragment
import baltamon.mx.kotlinpokedex.fragments.PokemonTypesFragment
import baltamon.mx.kotlinpokedex.fragments.PokemonesFragment
import baltamon.mx.kotlinpokedex.interfaces.PokeAPIClient
import baltamon.mx.kotlinpokedex.interfaces.buildGson
import baltamon.mx.kotlinpokedex.interfaces.buildRetrofit
import baltamon.mx.kotlinpokedex.models.Generation
import baltamon.mx.kotlinpokedex.models.NamedAPIResource
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var pokemonList = arrayListOf<NamedAPIResource>() //This should change to an object,
    var movesList = arrayListOf<NamedAPIResource>() // because all the three arrayList are
    var typesList = arrayListOf<NamedAPIResource>() // For the same object...

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()
        setUpNavigationView(this)
        loadPokemonGeneration()
    }

    fun setToolbar() {
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)

        supportActionBar?.title = getString(R.string.title_main_pokedex)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    fun setUpNavigationView(navigationListener: NavigationView.OnNavigationItemSelectedListener) =
            navigation_view.setNavigationItemSelectedListener(navigationListener)

    fun loadFragment(fragmentId: Int) {
        val replaceFragment = when (fragmentId) {
            1 -> PokemonesFragment.newInstance(pokemonList)
            2 -> PokemonMovesFragment.newInstance(movesList)
            3 -> PokemonTypesFragment.newInstance(typesList)
            else -> {
                showToast("No Fragment Selected")
            }
        }

        if (replaceFragment is Fragment) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, replaceFragment)
                    .commit()
        }
    }

    fun loadPokemonGeneration() {
        val dialog = ProgressDialog.show(this, getString(R.string.loading), "Loading, please wait...", true)

        val restClient = buildRetrofit(buildGson()).create(PokeAPIClient::class.java)
        val call = restClient.generation

        call.enqueue(object : Callback<Generation> {
            override fun onResponse(call: Call<Generation>?, response: Response<Generation>) {
                when (response.code()) {
                    200 -> {
                        response.body()?.let {
                            pokemonList = it.pokemon_species
                            movesList = it.moves
                            typesList = it.types
                        }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> toggleDrawer()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (isDrawerOpen()) toggleDrawer(false)
        else super.onBackPressed()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val toastMessage = when (item.itemId) {
            R.id.item_pokemons -> {
                loadFragment(1)
                "Pokemon"
            }
            R.id.item_moves -> {
                loadFragment(2)
                "Moves"
            }
            R.id.item_abilities -> "No Data"
            R.id.item_types -> {
                loadFragment(3)
                "Types"
            }
            else -> "No option selected"
        }

        showToast(toastMessage)
        toggleDrawer(false)

        return true
    }

    fun toggleDrawer(shouldOpen: Boolean = true) {
        if (shouldOpen) {
            drawer_layout.openDrawer(GravityCompat.START)
        } else {
            drawer_layout.closeDrawers()
        }
    }

    fun isDrawerOpen(): Boolean =
            drawer_layout.isDrawerOpen(GravityCompat.START)
}

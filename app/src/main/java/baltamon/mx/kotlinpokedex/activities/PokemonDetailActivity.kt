package baltamon.mx.kotlinpokedex.activities

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import baltamon.mx.kotlinpokedex.R
import baltamon.mx.kotlinpokedex.adapters.TabPokemonFragmentAdapter
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * Created by Baltazar Rodriguez on 28/05/2017.
 */
class PokemonDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        setUpToolbar()
        setUpTabView()
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

    fun setUpTabView(){
        view_pager.adapter = TabPokemonFragmentAdapter(supportFragmentManager)
        tab_layout.setupWithViewPager(view_pager)
    }
}
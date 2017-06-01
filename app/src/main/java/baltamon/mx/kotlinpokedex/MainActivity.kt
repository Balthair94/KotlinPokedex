package baltamon.mx.kotlinpokedex

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import baltamon.mx.kotlinpokedex.fragments.PokemonesFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()
        setUpNavigationView()
        loadFragment()
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
            R.id.item_pokemons -> showToast("Pokemons")
            R.id.item_moves -> showToast("Moves")
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

    fun loadFragment(){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, PokemonesFragment())
        fragmentTransaction.commit()
    }
}

package baltamon.mx.kotlinpokedex

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()
    }

    fun setToolbar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)

        val actionBar : ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.title = "Pokedex"
        }
    }
}

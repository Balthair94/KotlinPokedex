package baltamon.mx.kotlinpokedex.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import baltamon.mx.kotlinpokedex.MainActivity
import baltamon.mx.kotlinpokedex.R

/**
 * Created by Baltazar Rodriguez on 18/06/2017.
 */
class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 3000)
    }
}
package baltamon.mx.kotlinpokedex

import android.content.Context
import android.widget.Toast

/**
 * Created by Sinuhe_Jaime on 6/20/2017.
 */
fun Context.showToast(text: String){
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}
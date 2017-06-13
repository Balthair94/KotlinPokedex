package baltamon.mx.kotlinpokedex.interfaces

import baltamon.mx.kotlinpokedex.models.NamedAPIResourceList
import baltamon.mx.kotlinpokedex.models.Pokemon
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Baltazar Rodriguez on 11/06/2017.
 */
interface RestClient {
    @get:GET("pokemon/?limit=150")
    val pokemones: Call<NamedAPIResourceList>

    @get:GET("pokemon/{name}")
    val pokemon: Call<Pokemon>
}
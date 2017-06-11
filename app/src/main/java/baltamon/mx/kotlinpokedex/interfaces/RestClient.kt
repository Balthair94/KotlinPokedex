package baltamon.mx.kotlinpokedex.interfaces

import baltamon.mx.kotlinpokedex.models.APIResourceList
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Baltazar Rodriguez on 11/06/2017.
 */
interface RestClient {
    @get:GET("pokemon")
    val data: Call<APIResourceList>
}
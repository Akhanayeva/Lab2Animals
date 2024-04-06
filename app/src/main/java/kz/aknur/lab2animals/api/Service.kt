package kz.aknur.lab2animals.api

import kz.aknur.lab2animals.model.Animal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Service {
    @Headers("X-Api-Key:WzzGpqG9SL5I0An022z8AQ==FuhLT3JWDcxWnfpU")
    @GET("animals")
    fun getAnimalsByName(@Query("name") name: String): Call<List<Animal>>
}
package eu.marcelomorais.countries.restApi

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import eu.marcelomorais.countries.restApi.models.Country
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CountriesService {

    @GET("all")
    fun getAll(): Call<List<Country>>

    companion object {

        var BASE_URL = "https://restcountries.eu/rest/v2/"

        fun create() : CountriesService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(CountriesService::class.java)
        }
    }
}

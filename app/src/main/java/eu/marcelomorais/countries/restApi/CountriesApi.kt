package eu.marcelomorais.countries.restApi

import eu.marcelomorais.countries.restApi.models.Country
import eu.marcelomorais.countries.restApi.models.CountryDetails
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesService {

    @GET("all")
    fun getAll(): Call<List<Country>>

    @GET("name/{country}")
    fun getCountryByName(@Path("country") name: String):Call<List<Country>>

    @GET("name/{country}")
    fun getCountryDetail(@Path("country") name: String):Call<List<CountryDetails>>

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

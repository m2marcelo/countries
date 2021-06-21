package eu.marcelomorais.countries.restApi

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import eu.marcelomorais.countries.restApi.models.Country
import eu.marcelomorais.countries.restApi.models.CountryDetails
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call

private const val BASE_URL = "https://restcountries.eu/rest/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .client(CountriesHttpClient.getClient())
    .baseUrl(BASE_URL)
    .build()

interface CountriesRestAPI {
    @GET("all")
    suspend fun getAll(): List<Country>

    @GET("name/{country}")
    fun getCountryByName(@Path("country") name: String):List<Country>

    @GET("name/{country}")
    fun getCountryDetail(@Path("country") name: String):CountryDetails
}

object CountriesService {
    fun create(): CountriesRestAPI =
        retrofit.create(CountriesRestAPI::class.java)
}
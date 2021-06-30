package eu.marcelomorais.countries.database

import androidx.lifecycle.LiveData
import eu.marcelomorais.countries.repository.Outcome
import eu.marcelomorais.countries.restApi.models.Country
import eu.marcelomorais.countries.restApi.models.CountryDetails

interface CountriesDataSource {
    fun observerCountries(): LiveData<Outcome<List<CountriesDBModel>>>
    fun observerSearchCountries(country: String): LiveData<Outcome<List<CountriesDBModel>>>
    suspend fun getAllCountriesFromDB(): Outcome<List<CountriesDBModel>>
    suspend fun getCountriesByName(country: String): Outcome<List<CountriesDBModel>>
    suspend fun deleteCountries()
    suspend fun saveCountries(countries: List<CountriesDBModel>)
}

interface CountriesNetworkDataSource {
    fun observerCountries(): LiveData<Outcome<List<Country>>>
    fun observerCountryDetails(): LiveData<Outcome<List<CountryDetails>>>
    fun observerSearchCountries(country: String): LiveData<Outcome<List<CountriesDBModel>>>
    suspend fun getAllCountriesFromRest(): Outcome<List<Country>>
    suspend fun getCountriesByName(country: String): Outcome<List<CountriesDBModel>>
    suspend fun getCountryDetails(country: String): Outcome<List<CountryDetails>>
}

interface CountriesRepository {
    fun observerCountries(): LiveData<Outcome<List<CountriesDBModel>>>
    fun observerCountryDetails(): LiveData<Outcome<List<CountryDetails>>>
    fun observerSearchCountries(country: String): LiveData<Outcome<List<CountriesDBModel>>>
    suspend fun getAllCountriesFromDB(): Outcome<List<CountriesDBModel>>
    suspend fun getCountriesByName(country: String): Outcome<List<CountriesDBModel>>
    suspend fun getCountryDetails(country: String): Outcome<List<CountryDetails>>
    suspend fun refreshCountries()
}
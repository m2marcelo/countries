package eu.marcelomorais.countries.database

import androidx.lifecycle.LiveData
import eu.marcelomorais.countries.repository.Outcome
import eu.marcelomorais.countries.restApi.models.Country

interface CountriesDataSource {
    fun observerCountries(): LiveData<Outcome<List<CountriesDBModel>>>
    suspend fun getAllCountriesFromDB(): Outcome<List<CountriesDBModel>>
    suspend fun getCountriesById(countryId: Int): Outcome<List<CountriesDBModel?>>
    suspend fun deleteCountries()
}

interface CountriesNetworkDataSource {
    fun observerCountries(): LiveData<Outcome<List<Country>>>
    suspend fun getAllCountriesFromRest(): Outcome<List<Country>>
    suspend fun getCountriesByName(country: String): Outcome<List<Country?>>
    suspend fun deleteCountries()
}

interface CountriesRepository {
    fun observerCountries(): LiveData<Outcome<List<CountriesDBModel>>>
    suspend fun getAllCountriesFromDB(): Outcome<List<CountriesDBModel>>
    suspend fun getCountriesById(countryId: Int): Outcome<List<CountriesDBModel?>>
    suspend fun deleteCountries()
}
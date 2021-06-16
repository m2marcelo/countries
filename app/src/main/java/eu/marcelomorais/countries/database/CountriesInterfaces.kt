package eu.marcelomorais.countries.database

import androidx.lifecycle.LiveData
import eu.marcelomorais.countries.repository.Outcome

interface CountriesDataSource {
    fun observerCountries(): LiveData<Outcome<List<CountriesDBModel>>>
    suspend fun getAllCountriesFromDB(): Outcome<List<CountriesDBModel>>
    suspend fun getCountriesById(countryId: Int): Outcome<List<CountriesDBModel?>>
    suspend fun deleteCountries()
}

interface CountriesRepository {
    fun observerCountries(): LiveData<Outcome<List<CountriesDBModel>>>
    suspend fun getAllCountriesFromDB(): Outcome<List<CountriesDBModel>>
    suspend fun getCountriesById(countryId: Int): Outcome<List<CountriesDBModel?>>
    suspend fun deleteCountries()
}
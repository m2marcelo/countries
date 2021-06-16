package eu.marcelomorais.countries.database

import androidx.lifecycle.LiveData

interface CountriesDataSource {
    fun observerElections(): LiveData<List<CountriesDBModel>>
    suspend fun getAllCountriesFromDB(): List<CountriesDBModel>
    suspend fun getCountriesById(countryId: Int): List<CountriesDBModel?>
    suspend fun deleteCountries()
}
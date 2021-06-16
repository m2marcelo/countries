package eu.marcelomorais.countries.repository

import androidx.lifecycle.LiveData
import eu.marcelomorais.countries.database.CountriesDBModel
import eu.marcelomorais.countries.database.CountriesDataSource
import eu.marcelomorais.countries.restApi.CountriesService
import kotlinx.coroutines.CoroutineDispatcher

class RemoteDataSource (
    private val apiService: CountriesService,
    private val ioDispatcher: CoroutineDispatcher
        ) : CountriesDataSource {

    override fun observerCountries(): LiveData<Outcome<List<CountriesDBModel>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCountriesFromDB(): Outcome<List<CountriesDBModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCountriesById(countryId: Int): Outcome<List<CountriesDBModel?>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCountries() {
        TODO("Not yet implemented")
    }
}
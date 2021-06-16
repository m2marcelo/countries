package eu.marcelomorais.countries.database

import androidx.lifecycle.LiveData
import eu.marcelomorais.countries.repository.Outcome
import kotlinx.coroutines.CoroutineDispatcher

class LocalDataSource(
    private val database: CountriesDao,
    private val ioDispatcher: CoroutineDispatcher,
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
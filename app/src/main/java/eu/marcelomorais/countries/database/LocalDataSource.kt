package eu.marcelomorais.countries.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import eu.marcelomorais.countries.repository.Outcome
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.Exception

class LocalDataSource(
    private val database: CountriesDao,
    private val ioDispatcher: CoroutineDispatcher,
) : CountriesDataSource {
    override fun observerCountries(): LiveData<Outcome<List<CountriesDBModel>>> {
        return database.getAllCountriesFromDB().map {
            Outcome.Success(it)
        }
    }

    override suspend fun getAllCountriesFromDB(): Outcome<List<CountriesDBModel>> {
        return withContext(ioDispatcher) {
            return@withContext try {
                Outcome.Success(database.getAllCountriesFromDBSync())
            } catch (exception: Exception) {
                Outcome.Error(exception)
            }
        }
    }

    override suspend fun getCountriesByName(country: String): Outcome<List<CountriesDBModel?>> {
        return withContext(ioDispatcher) {
            return@withContext try {
                Outcome.Success(database.getCountriesByName(country))
            } catch (exception: Exception) {
                Outcome.Error(exception)
            }
        }
    }

    override suspend fun deleteCountries() {
        withContext(ioDispatcher) { database.deleteCountries() }
    }

    override suspend fun saveCountries(countries: List<CountriesDBModel>) {
        withContext(ioDispatcher) {
            database.saveAll(countries)
        }
    }
}
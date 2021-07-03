package eu.marcelomorais.countries.repository

import androidx.lifecycle.LiveData
import eu.marcelomorais.countries.database.CountriesDBModel
import eu.marcelomorais.countries.database.CountriesRepository
import eu.marcelomorais.countries.database.LocalDataSource
import eu.marcelomorais.countries.restApi.models.CountryDetails
import eu.marcelomorais.countries.restApi.models.convertToDBModel
import eu.marcelomorais.countries.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.Exception

class Repository (
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
): CountriesRepository {
    override fun observerCountries() = localDataSource.observerCountries()
    override fun observerCountryDetails() = remoteDataSource.observerCountryDetails()
    override fun observerSearchCountries() = remoteDataSource.observerSearchCountries()
    override fun observerMyCountry() = remoteDataSource.observerMyCountry()

    override suspend fun getAllCountriesFromDB(): Outcome<List<CountriesDBModel>> {
        wrapEspressoIdlingResource {
            return localDataSource.getAllCountriesFromDB()
        }
    }

    override suspend fun getCountriesByName(country: String): Outcome<List<CountriesDBModel>> {
        wrapEspressoIdlingResource {
            return remoteDataSource.getCountriesByName(country)
        }
    }

    override suspend fun getMyCountry(country: String): Outcome<List<CountriesDBModel>> {
        wrapEspressoIdlingResource {
            return remoteDataSource.getMyCountry(country)
        }
    }

    override suspend fun getCountryDetails(country: String): Outcome<List<CountryDetails>> {
        wrapEspressoIdlingResource {
            return withContext(ioDispatcher) {
                remoteDataSource.getCountryDetails(country)
            }
        }
    }

    override suspend fun refreshCountries() {
        wrapEspressoIdlingResource {
            try {
                when (val currentContent = remoteDataSource.getAllCountriesFromRest()) {
                    is Outcome.Success -> {
                        localDataSource.deleteCountries()
                        val newDBContent = currentContent.value.convertToDBModel()
                        return withContext(ioDispatcher) {
                            localDataSource.saveCountries(newDBContent)
                        }
                    }
                    is Outcome.Error -> throw currentContent.cause!!
                }
            } catch (exception: Exception) {
                throw exception
            }
        }
    }
}
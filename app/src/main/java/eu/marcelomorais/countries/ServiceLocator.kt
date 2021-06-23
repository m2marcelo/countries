package eu.marcelomorais.countries

import android.content.Context
import eu.marcelomorais.countries.database.CountriesDatabase
import eu.marcelomorais.countries.database.CountriesRepository
import eu.marcelomorais.countries.database.LocalDataSource
import eu.marcelomorais.countries.repository.RemoteDataSource
import eu.marcelomorais.countries.repository.Repository
import eu.marcelomorais.countries.restApi.CountriesService
import kotlinx.coroutines.Dispatchers

object ServiceLocator {
    @Volatile
    var countriesRepository: CountriesRepository? = null

    fun provideCountriesRespository(context: Context) : CountriesRepository {
        synchronized(this) {
            return countriesRepository ?: createCountriesRepository(context)
        }
    }

    private fun createCountriesRepository(context: Context): CountriesRepository {
        val restApiService = CountriesService
        val database = CountriesDatabase.getInstance(context).countriesDao
        val newRepo = Repository(
            LocalDataSource(database, Dispatchers.IO),
            RemoteDataSource(restApiService, Dispatchers.IO),
            Dispatchers.IO
        )
        countriesRepository = newRepo
        return newRepo
    }
}

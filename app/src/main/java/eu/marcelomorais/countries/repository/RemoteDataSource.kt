package eu.marcelomorais.countries.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import eu.marcelomorais.countries.database.CountriesDBModel
import eu.marcelomorais.countries.database.CountriesNetworkDataSource
import eu.marcelomorais.countries.restApi.CountriesService
import eu.marcelomorais.countries.restApi.models.Country
import eu.marcelomorais.countries.restApi.models.CountryDetails
import eu.marcelomorais.countries.restApi.models.convertToDBModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.Exception

class RemoteDataSource (
    private val apiService: CountriesService,
    private val ioDispatcher: CoroutineDispatcher
        ) : CountriesNetworkDataSource {

    private val _allCountries: MutableLiveData<Outcome<List<Country>>> = MutableLiveData()
    private val _countrySearchResults: MutableLiveData<Outcome<List<CountriesDBModel>>> = MutableLiveData()
    private val _myCountryResult: MutableLiveData<Outcome<List<CountriesDBModel>>> = MutableLiveData()
    private val _CountryDetails: MutableLiveData<Outcome<List<CountryDetails>>> = MutableLiveData()


    override fun observerCountries(): LiveData<Outcome<List<Country>>> {
        return _allCountries
    }

    override fun observerCountryDetails(): LiveData<Outcome<List<CountryDetails>>> {
        return _CountryDetails
    }

    override fun observerSearchCountries(): LiveData<Outcome<List<CountriesDBModel>>> {
        return _countrySearchResults
    }

    override fun observerMyCountry(): LiveData<Outcome<List<CountriesDBModel>>> {
        return _myCountryResult
    }

    override suspend fun getAllCountriesFromRest(): Outcome<List<Country>> {
        return withContext(ioDispatcher) {
            val result = try {
                Outcome.Success(apiService.create().getAll())
            } catch (ex: Exception) {
                Outcome.Error(ex)
            }
            _allCountries.postValue(result)
            result
        }
    }

    override suspend fun getCountriesByName(country: String): Outcome<List<CountriesDBModel>> {
        return withContext(ioDispatcher) {
            val result = try {
                Outcome.Success(apiService.create().getCountryByName(country).convertToDBModel())
            } catch (ex: Exception) {
                Outcome.Error(ex)
            }
            _countrySearchResults.postValue(result)
            result
        }
    }

    override suspend fun getMyCountry(country: String): Outcome<List<CountriesDBModel>> {
        return withContext(ioDispatcher) {
            val result = try {
                Outcome.Success(apiService.create().getCountryByName(country).convertToDBModel())
            } catch (ex: Exception) {
                Outcome.Error(ex)
            }
            _myCountryResult.postValue(result)
            result
        }
    }

    override suspend fun getCountryDetails(country: String): Outcome<List<CountryDetails>> {
        return withContext(ioDispatcher) {
            val result = try {
                Outcome.Success(apiService.create().getCountryDetail(country))
            } catch (ex: Exception) {
                ex.printStackTrace()
                Outcome.Error(ex)
            }
            _CountryDetails.postValue(result)
            result
        }
    }
}

package eu.marcelomorais.countries.countryview

import android.util.Log
import androidx.lifecycle.*
import eu.marcelomorais.countries.database.CountriesDBModel
import eu.marcelomorais.countries.database.CountriesRepository
import eu.marcelomorais.countries.repository.Outcome
import eu.marcelomorais.countries.restApi.models.CountryDetails
import kotlinx.coroutines.launch

class CountryDetailsViewModel(
    private val repository: CountriesRepository,
    private val country: String
): ViewModel() {

    lateinit var details: CountryDetails

    private val countryDetails: LiveData<List<CountryDetails>> =
        Transformations.map(repository.observerCountryDetails()) {
            when (it) {
                is Outcome.Success -> {
                    it.value
                }
                is Outcome.Error -> {
                    emptyList()
                }
            }
        }

    val currentCountryDetails: LiveData<List<CountryDetails>> = countryDetails

    init {
        fetchCountryData(country)
    }

    fun fetchCountryData(country: String) {
        viewModelScope.launch {
            fetchCountryDetails(country)
        }
    }

    private suspend fun fetchCountryDetails(country: String) {
        repository.getCountryDetails(country)
    }
}

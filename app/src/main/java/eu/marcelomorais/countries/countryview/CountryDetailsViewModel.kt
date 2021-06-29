package eu.marcelomorais.countries.countryview

import androidx.lifecycle.*
import eu.marcelomorais.countries.database.CountriesRepository
import eu.marcelomorais.countries.repository.Outcome
import eu.marcelomorais.countries.restApi.models.CountryDetails
import kotlinx.coroutines.launch

class CountryDetailsViewModel(
    private val repository: CountriesRepository
): ViewModel() {

    private val countryDetails: LiveData<CountryDetails> =
        Transformations.map(repository.observerCountryDetails()) {
            when (it) {
                is Outcome.Success -> {
                    it.value
                }
                is Outcome.Error -> {
                    null
                }
            }
        }

    fun fetchCountryDetails(country: String) {
        viewModelScope.launch {
            val response = repository.getCountryDetails(country)
        }
    }
}

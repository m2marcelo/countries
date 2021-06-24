package eu.marcelomorais.countries.countryview

import android.util.Log
import androidx.lifecycle.*
import eu.marcelomorais.countries.database.CountriesDBModel
import eu.marcelomorais.countries.database.CountriesRepository
import eu.marcelomorais.countries.repository.Outcome
import kotlinx.coroutines.launch

class CountriesViewModel(private val repository: CountriesRepository) : ViewModel() {

    private val countriesList: LiveData<List<CountriesDBModel>> =
        Transformations.map(repository.observerCountries()) {
            when (it) {
                is Outcome.Error -> {
                    emptyList()
                }
                is Outcome.Success -> {
                    it.value
                }
            }
    }

    val currentCountriesList: LiveData<List<CountriesDBModel>> = countriesList

    init {
       updateCountriesData()
    }

    private fun updateCountriesData() {
        viewModelScope.launch {
            updateCountries()
        }
    }

    private suspend fun updateCountries() {
        repository.refreshCountries()
    }
}

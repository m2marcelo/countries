package eu.marcelomorais.countries.countryview

import android.util.Log
import androidx.lifecycle.*
import androidx.navigation.NavDirections
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
    private val _navigateTo = MutableLiveData<NavDirections?>()
    val navigateTo: LiveData<NavDirections?> = _navigateTo

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

    fun clearNavigationLiveData() {
        _navigateTo.value = null
    }

    fun onCountryItemClicked(country: String) {
        _navigateTo.value = CountriesFragmentDirections
            .actionCountriesFragmentToCountryDetailsFragment(country)
    }
}

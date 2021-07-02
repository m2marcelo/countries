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
                is Outcome.Loading -> {
                    emptyList()
                }
            }
    }
    private val _navigateTo = MutableLiveData<NavDirections?>()
    val navigateTo: LiveData<NavDirections?> = _navigateTo

    private val _loading = MutableLiveData<Boolean>(true)
    val loading: LiveData<Boolean> = _loading

    val currentCountriesList: LiveData<List<CountriesDBModel>> = countriesList

    init {
        showProgressBar(true)
        updateCountriesData()
    }

    private fun updateCountriesData() {
        showProgressBar(true)
        viewModelScope.launch {
            updateCountries()
            showProgressBar(false)
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

    fun showProgressBar(value: Boolean) {
        _loading.postValue(value)
    }
}

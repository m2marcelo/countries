package eu.marcelomorais.countries.countryview

import android.util.Log
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import eu.marcelomorais.countries.database.CountriesDBModel
import eu.marcelomorais.countries.database.CountriesRepository
import eu.marcelomorais.countries.repository.Outcome
import kotlinx.coroutines.launch


class SearchCountryViewModel(private val repository: CountriesRepository) : ViewModel() {
    private val _countryName = MutableLiveData("")
    val countryName: MutableLiveData<String> = _countryName

    private val searchCountriesList: LiveData<List<CountriesDBModel>> =
        Transformations.map(repository.observerSearchCountries(countryName.value.toString())) {
            when (it) {
                is Outcome.Error -> {
                    emptyList()
                }
                is Outcome.Success -> {
                    Log.d("countriesList trans", "Success")
                    it.value
                }
            }
        }

    val currentSearchList: LiveData<List<CountriesDBModel>> = searchCountriesList

    private val _navigateTo = MutableLiveData<NavDirections?>()
    val navigateTo: LiveData<NavDirections?> = _navigateTo

    fun searchCountries() {
        val country: String = countryName.value.toString()

        Log.d("SearchCountryViewModel", "country = $country")

        country?.let {
            viewModelScope.launch {
                remoteSearchCountries(it)
            }
        }
    }

    private suspend fun remoteSearchCountries(countryName: String) {
        Log.d("SearchCountryViewModel", "remoteSearchCountries = $countryName")
        repository.getCountriesByName(countryName)
        Log.d("SearchCountryViewModel", "currentSearchList  = ${currentSearchList.value}")
    }

    fun clearNavigationLiveData() {
        _navigateTo.value = null
    }

    fun onCountryItemClicked(country: String) {
        _navigateTo.value = SearchCountryFragmentDirections
            .actionSearchCountryFragmentToCountryDetailsFragment(country)
    }
}



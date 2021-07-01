package eu.marcelomorais.countries.countryview

import android.util.Log
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import eu.marcelomorais.countries.database.CountriesDBModel
import eu.marcelomorais.countries.database.CountriesRepository
import eu.marcelomorais.countries.repository.Outcome
import eu.marcelomorais.countries.restApi.models.CurrentCountry
import kotlinx.coroutines.launch


class MyCountryViewModel (private val repository: CountriesRepository) : ViewModel() {

    private val _myCountryDestinations = MutableLiveData<NavDirections?>()
    val myCountryDestinations: LiveData<NavDirections?> = _myCountryDestinations

    private val myCountryLiveData: LiveData<List<CountriesDBModel>> =
        Transformations.map(repository.observerSearchCountries()) {
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

    val myCountryInfo: LiveData<List<CountriesDBModel>> = myCountryLiveData

    fun getCurrentCountry(country: CurrentCountry) {
        Log.d("MyCountryViewModel", "getCurrentCountry")
        viewModelScope.launch {
            remoteGetCurrentData(country.countryName)
        }
    }

    private suspend fun remoteGetCurrentData(countryName: String) {
        Log.d("MyCountryViewModel", "remoteGetCurrentData = $countryName")
        repository.getCountriesByName(countryName)
    }

    fun clearMyCountryNavigationLiveData() {
        _myCountryDestinations.value = null
    }

    fun onNavigateToCountryDetails() {
        if(null != myCountryInfo.value?.get(0)) {
            val country: String = myCountryInfo.value!!.get(0).countryName
            _myCountryDestinations.value = MyCountryFragmentDirections
                .actionMyCountryFragmentToCountryDetailsFragment(country)
        }

    }
}
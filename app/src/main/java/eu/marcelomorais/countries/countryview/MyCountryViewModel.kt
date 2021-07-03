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

    private var _currentCountry= CurrentCountry("", 0.0, 0.0)

    private val myCountryLiveData: LiveData<List<CountriesDBModel>> =
        Transformations.map(repository.observerMyCountry()) {
            when (it) {
                is Outcome.Error -> {
                    emptyList()
                }
                is Outcome.Success -> {
                    it.value
                }
            }
        }

    val myCountryInfo: LiveData<List<CountriesDBModel>> = myCountryLiveData
    private val _loading = MutableLiveData<Boolean>(true)
    val loading: LiveData<Boolean> = _loading

    fun getCurrentCountry(country: CurrentCountry) {
        _currentCountry = country
        showProgressBar(true)
        viewModelScope.launch {
            remoteGetCurrentData(country.countryName)
            showProgressBar(false)
        }
        showProgressBar(false)
    }

    private suspend fun remoteGetCurrentData(countryName: String) {
        repository.getMyCountry(countryName)
    }

    fun clearMyCountryNavigationLiveData() {
        _myCountryDestinations.value = null
    }

    fun onNavigateToCountryDetails() {
        if(null != myCountryInfo.value?.get(0)) {
            val country: String = myCountryInfo.value!![0].countryName
            _myCountryDestinations.value = MyCountryFragmentDirections
                .actionMyCountryFragmentToCountryDetailsFragment(country)
        }
    }

    fun onNavigateToMapFragment() {
        if( null != myCountryInfo.value?.get(0) ) {
            _myCountryDestinations.value = MyCountryFragmentDirections
                .actionMyCountryFragmentToMapsFragment(_currentCountry)
        }
    }

    fun showProgressBar(value: Boolean) {
        _loading.postValue(value)
    }
}
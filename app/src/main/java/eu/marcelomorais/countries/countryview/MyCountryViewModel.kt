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
                    Log.d("countriesList trans", "Success")
                    it.value
                }
                is Outcome.Loading -> {
                    Log.d("myCountryLiveData", "Outcome.Loading")
                    emptyList()
                }
            }
        }

    val myCountryInfo: LiveData<List<CountriesDBModel>> = myCountryLiveData
    private val _loading = MutableLiveData<Boolean>(true)
    val loading: LiveData<Boolean> = _loading

    fun getCurrentCountry(country: CurrentCountry) {
        Log.d("MyCountryViewModel", "getCurrentCountry")
        _currentCountry = country
        showProgressBar(true)
        Log.d("MyCountryViewModel", "value = {$loading}")

        viewModelScope.launch {
            remoteGetCurrentData(country.countryName)
            showProgressBar(false)
            Log.d("MyCountryViewModel after", "value = {$loading.value}")
        }
    }

    private suspend fun remoteGetCurrentData(countryName: String) {
        Log.d("MyCountryViewModel", "remoteGetCurrentData = $countryName")
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
            val country: String = myCountryInfo.value!![0].countryName

            _myCountryDestinations.value = MyCountryFragmentDirections
                .actionMyCountryFragmentToMapsFragment(_currentCountry)
        }
    }

    fun showProgressBar(value: Boolean) {
        _loading.postValue(value)
    }
}
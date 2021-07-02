package eu.marcelomorais.countries.countryview

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
        Transformations.map(repository.observerSearchCountries()) {
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

    val currentSearchList: LiveData<List<CountriesDBModel>> = searchCountriesList

    private val _navigateTo = MutableLiveData<NavDirections?>()
    val navigateTo: LiveData<NavDirections?> = _navigateTo

    private val _loading = MutableLiveData<Boolean>(true)
    val loading: LiveData<Boolean> = _loading

    var listSize = currentSearchList.value?.size
    private val _resultSize = MutableLiveData<Int>(-1)
    val resultSize: LiveData<Int> = _resultSize

    init {
        showProgressBar(false)
    }

    fun searchCountries() {
        val country: String = countryName.value.toString()
        _resultSize.postValue(-1)

        showProgressBar(true)
        country?.let {
            viewModelScope.launch {
                remoteSearchCountries(it)
                listSize = currentSearchList.value?.size
                _resultSize.postValue(listSize)
                showProgressBar(false)
            }
        }
    }

    private suspend fun remoteSearchCountries(countryName: String) {
        repository.getCountriesByName(countryName)
    }

    fun clearNavigationLiveData() {
        _navigateTo.value = null
    }

    fun onCountryItemClicked(country: String) {
        _navigateTo.value = SearchCountryFragmentDirections
            .actionSearchCountryFragmentToCountryDetailsFragment(country)
    }

    fun showProgressBar(value: Boolean) {
        _loading.postValue(value)
    }
}



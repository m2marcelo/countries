package eu.marcelomorais.countries.countryview

import androidx.lifecycle.*
import eu.marcelomorais.countries.database.CountriesRepository
import eu.marcelomorais.countries.repository.Outcome
import eu.marcelomorais.countries.restApi.models.CountryDetails
import kotlinx.coroutines.launch

class CountryDetailsViewModel(
    private val repository: CountriesRepository,
    private val country: String
): ViewModel() {


    private val _loading = MutableLiveData<Boolean>(true)
    private val _callingCode = MutableLiveData<String>("")
    private val _borders = MutableLiveData<String>("")
    private val _timezone = MutableLiveData<String>("")

    val loading: LiveData<Boolean> = _loading
    val callingCode: LiveData<String> = _callingCode
    val borders: LiveData<String> = _borders
    val timezone: LiveData<String> = _timezone

    private val countryDetails: LiveData<List<CountryDetails>> =
        Transformations.map(repository.observerCountryDetails()) {
            when (it) {
                is Outcome.Success -> {
                    it.value
                }
                is Outcome.Error -> {
                    emptyList()
                }
                is Outcome.Loading -> {
                    emptyList()
                }
            }
        }

    val currentCountryDetails: LiveData<List<CountryDetails>> = countryDetails

    init {
        fetchCountryData(country)
    }

    fun fetchCountryData(country: String) {
        showProgressBar(true)
        viewModelScope.launch {
            fetchCountryDetails(country)
            showProgressBar(false)
        }
    }

    fun showProgressBar(value: Boolean) {
        _loading.postValue(value)
    }

    fun updateDetails() {
        getCallingCodes()
        getBorders()
        getTimezone()
    }

    private fun getCallingCodes() {
        val listIterator = currentCountryDetails.value?.get(0)?.callingCodes?.iterator()
        _callingCode.postValue(getFormattedData(listIterator))
    }

    private fun getBorders() {
        val listIterator = currentCountryDetails.value?.get(0)?.borders?.iterator()
        _borders.postValue(getFormattedData(listIterator))
    }

    private fun getTimezone() {
        val listIterator = currentCountryDetails.value?.get(0)?.timezones?.iterator()
        _timezone.postValue(getFormattedData(listIterator))
    }

    private fun getFormattedData(iterator: Iterator<String>?) : String {
        var result: String = ""
        if (iterator != null) {
            while (iterator.hasNext()) {
                result += iterator.next()
                result += " "
            }
        }

        return result
    }

    private suspend fun fetchCountryDetails(country: String) {
        repository.getCountryDetails(country)
    }
}

package eu.marcelomorais.countries.countryview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.marcelomorais.countries.database.CountriesRepository

@Suppress("UNCHECKED_CAST")
class MyCountryViewModelFactory (
    private val repository: CountriesRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (MyCountryViewModel(repository) as T)
}
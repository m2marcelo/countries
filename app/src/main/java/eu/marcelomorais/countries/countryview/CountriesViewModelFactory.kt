package eu.marcelomorais.countries.countryview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.marcelomorais.countries.database.CountriesDao
import eu.marcelomorais.countries.database.CountriesRepository

@Suppress("UNCHECKED_CAST")
class CountriesViewModelFactory (
    private val repository: CountriesRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        (CountriesViewModel(repository) as T)
}
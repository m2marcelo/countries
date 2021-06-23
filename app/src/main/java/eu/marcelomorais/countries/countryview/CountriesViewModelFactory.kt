package eu.marcelomorais.countries.countryview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.marcelomorais.countries.database.CountriesDao

class CountriesViewModelFactory(
    private val dataSource: CountriesDao,
    private val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountriesViewModel::class.java)) {
            return CountriesViewModel(dataSource ,app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}

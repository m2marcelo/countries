package eu.marcelomorais.countries.countryview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CountriesViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountriesViewModel::class.java)) {
            return CountriesViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}

package eu.marcelomorais.countries.countryview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SearchCountryViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchCountryViewModel::class.java)) {
            return SearchCountryViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}
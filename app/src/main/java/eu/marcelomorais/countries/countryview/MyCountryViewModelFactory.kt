package eu.marcelomorais.countries.countryview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyCountryViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyCountryViewModel::class.java)) {
            return MyCountryViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}
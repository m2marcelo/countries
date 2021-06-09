package eu.marcelomorais.countries.countryview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CountriesViewModel(application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel

    init {
        viewModelScope.launch {  }
    }

}
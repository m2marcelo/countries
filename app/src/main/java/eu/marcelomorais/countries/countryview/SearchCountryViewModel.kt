package eu.marcelomorais.countries.countryview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SearchCountryViewModel (application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel
    init {
        viewModelScope.launch {  }
    }
}
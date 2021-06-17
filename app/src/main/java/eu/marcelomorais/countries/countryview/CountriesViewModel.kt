package eu.marcelomorais.countries.countryview

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import eu.marcelomorais.countries.restApi.CountriesService
import kotlinx.coroutines.launch
import java.lang.Exception

class CountriesViewModel(application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel

    init {
       getAllCountries()
    }

    fun getAllCountries() {
        Log.d("CountriesViewModel", "testApi")
        viewModelScope.launch {
            try {
                var listResult = CountriesService.create().getAll()
                Log.d("CountriesViewModel", "testApi result = " + listResult)
            } catch (e: Exception) {
                Log.d("CountriesViewModel", "Exception = $e")
            }
        }
    }
}
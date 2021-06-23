package eu.marcelomorais.countries.countryview

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.marcelomorais.countries.database.CountriesDBModel
import eu.marcelomorais.countries.database.CountriesDao
import eu.marcelomorais.countries.database.CountriesRepository
import eu.marcelomorais.countries.restApi.CountriesService
import kotlinx.coroutines.launch
import java.lang.Exception

class CountriesViewModel(private val repository: CountriesRepository) : ViewModel() {

    init {
//       getAllCountries()
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

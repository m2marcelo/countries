package eu.marcelomorais.countries.countryview

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.marcelomorais.countries.database.CountriesRepository
import eu.marcelomorais.countries.restApi.models.CurrentCountry
import kotlinx.coroutines.launch


class MyCountryViewModel (private val repository: CountriesRepository) : ViewModel() {
    // TODO: Implement the ViewModel
    init {
        viewModelScope.launch {  }
    }

    fun getCurrentCountry(country: CurrentCountry) {
        Log.d("MyCountryViewModel", "getCurrentCountry")
//        val apiInterface = CountriesService.create().getCountryDetail(country.countryName)

//        apiInterface.enqueue(object : Callback<List<CountryDetails>> {
//            override fun onResponse(
//                call: Call<List<CountryDetails>>?,
//                response: Response<List<CountryDetails>>?
//            ) {
//                if (response?.body() != null)
//                    Log.d("MyCountryViewModel", "testApi result = " + response.body())
//                else
//                    Log.d("MyCountryViewModel", "response?.body() " + response)
//            }
//
//            override fun onFailure(call: Call<List<CountryDetails>>?, t: Throwable?) {
//                Log.d("onFailure", "falhou Call -> " + call)
//                Log.d("onFailure", "falhou Throwable -> " + call + t)
//            }
//        })
    }
}
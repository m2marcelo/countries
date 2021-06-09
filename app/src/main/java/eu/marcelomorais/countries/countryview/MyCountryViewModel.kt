package eu.marcelomorais.countries.countryview

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import eu.marcelomorais.countries.restApi.CountriesService
import eu.marcelomorais.countries.restApi.models.Country
import eu.marcelomorais.countries.restApi.models.CountryDetails
import eu.marcelomorais.countries.restApi.models.CurrentCountry
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyCountryViewModel (application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel
    init {
        viewModelScope.launch {  }
    }

    fun getCurrentCountry(country: CurrentCountry) {
        Log.d("MyCountryViewModel", "getCurrentCountry")
        val apiInterface = CountriesService.create().getCountryDetail(country.countryName)

        apiInterface.enqueue(object : Callback<List<CountryDetails>> {
            override fun onResponse(
                call: Call<List<CountryDetails>>?,
                response: Response<List<CountryDetails>>?
            ) {
                if (response?.body() != null)
                    Log.d("MyCountryViewModel", "testApi result = " + response.body())
                else
                    Log.d("MyCountryViewModel", "response?.body() " + response)
            }

            override fun onFailure(call: Call<List<CountryDetails>>?, t: Throwable?) {
                Log.d("onFailure", "falhou Call -> " + call)
                Log.d("onFailure", "falhou Throwable -> " + call + t)
            }
        })
    }
}
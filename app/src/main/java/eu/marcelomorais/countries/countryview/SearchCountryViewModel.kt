package eu.marcelomorais.countries.countryview

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import eu.marcelomorais.countries.restApi.CountriesService
import eu.marcelomorais.countries.restApi.models.Country
import eu.marcelomorais.countries.utils.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchCountryViewModel (application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel
    private val _countryName = MutableLiveData("")
    val countryName: MutableLiveData<String> = _countryName

    fun searchCountry() {
        Log.d("SearchCountryViewModel", "getCurrentCountry countryName = " + countryName.value)
        if (countryName.value == "") {
            Log.d("searchCountry", "error country name null")
            return
        }

        val country : String = countryName.value.toString()

        val apiInterface = country?.let { CountriesService.create().getCountryByName(it) }

        apiInterface.enqueue(object : Callback<List<Country>> {
            override fun onResponse(
                call: Call<List<Country>>?,
                response: Response<List<Country>>?
            ) {
                if (response?.body() != null)
                    Log.d("SearchCountryViewModel", "testApi result = " + response.body())
                else
                    Log.d("SearchCountryViewModel", "response?.body() $response")
            }

            override fun onFailure(call: Call<List<Country>>?, t: Throwable?) {
                Log.d("SearchCountryViewModel onFailure", "falhou Call -> " + call)
                Log.d("SearchCountryViewModel onFailure", "falhou Throwable -> " + call + t)
            }
        })
    }


}
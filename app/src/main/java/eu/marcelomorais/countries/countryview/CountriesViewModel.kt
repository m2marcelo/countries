package eu.marcelomorais.countries.countryview

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import eu.marcelomorais.countries.restApi.CountriesService
import eu.marcelomorais.countries.restApi.models.Country
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountriesViewModel(application: Application) : AndroidViewModel(application) {
    // TODO: Implement the ViewModel

    init {
        viewModelScope.launch { getAllCountries() }
    }

    fun getAllCountries() {
        Log.d("CountriesViewModel", "testApi")
        val apiInterface = CountriesService.create().getAll()

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue( object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>?, response: Response<List<Country>>?) {

                if(response?.body() != null)
                    Log.d("CountriesViewModel", "testApi result = " + response.body())
            }

            override fun onFailure(call: Call<List<Country>>?, t: Throwable?) {

            }
        })

    }

}
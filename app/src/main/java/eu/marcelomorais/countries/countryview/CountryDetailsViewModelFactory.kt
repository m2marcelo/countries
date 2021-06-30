package eu.marcelomorais.countries.countryview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import eu.marcelomorais.countries.database.CountriesRepository

@Suppress("UNCHECKED_CAST")
class CountryDetailsViewModelFactory  (
        private val repository: CountriesRepository,
        private val country: String
) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>) =
            (CountryDetailsViewModel(repository, country) as T)
}


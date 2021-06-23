package eu.marcelomorais.countries

import android.app.Application
import eu.marcelomorais.countries.database.CountriesRepository

class CountriesApp: Application() {
    val countriesRepository: CountriesRepository
        get() = ServiceLocator.provideCountriesRespository(this)

    override fun onCreate() {
        super.onCreate()
    }
}

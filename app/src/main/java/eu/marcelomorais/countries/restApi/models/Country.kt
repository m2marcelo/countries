package eu.marcelomorais.countries.restApi.models

import com.squareup.moshi.JsonClass
import eu.marcelomorais.countries.database.CountriesDBModel

@JsonClass(generateAdapter = true)
data class Country(
    val id: Int,
    val name: String,
    val capital: String,
    val region: String,
    val flag: String
)

fun Country.convertToDBModel() =
    CountriesDBModel (
        id = id,
        countryName = name,
        countryCapital =capital,
        countryRegion = region,
        countryFlag = flag
    )
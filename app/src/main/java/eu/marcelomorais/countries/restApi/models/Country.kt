package eu.marcelomorais.countries.restApi.models

import com.squareup.moshi.JsonClass
import eu.marcelomorais.countries.database.CountriesDBModel

@JsonClass(generateAdapter = true)
data class Country(
    val id: Int?,
    val name: String?,
    val capital: String?,
    val region: String?,
    val flag: String?
)

fun List<Country>.convertToDBModel() : List<CountriesDBModel> {
    return map{
        CountriesDBModel(
            it.id ?: 0,
            it.name ?: "",
            it.capital ?: "",
            it.region ?: "",
            it.flag ?: ""
        )
    }
}
